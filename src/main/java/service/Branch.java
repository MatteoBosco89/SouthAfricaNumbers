package service;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.nio.file.Files;
import java.nio.file.Path;

import model.TelNum;
import model.TelNumRepository;

@Service
public class Branch {
	
	@Autowired
	private TelNumRepository tnr;
	
	public Branch() {}
	
	public TelNum SingleNumParser(String num) {
		TelNum n = new TelNum();
		n.setOriginalNum(num);
		n.checkNum();
		tnr.save(n);
		return n;
	}
	
	public void CSVParser(InputStream file) {
		try (CSVReader csvReader = new CSVReader(new InputStreamReader(file))) {
			csvReader.readNext(); // skip header (id,sms_phone)
		    String[] values = null;
		    while ((values = csvReader.readNext()) != null) {
		    	SingleNumParser(values[1]);
		    }
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public Iterable<TelNum> allNum() {
		return tnr.findAll();
	}
	
	public Path tempFile() {
        try {
            Path p =  Files.createTempFile("Numbers", ".csv");
            Writer writer = Files.newBufferedWriter(p);
            try (CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {
				Iterable<TelNum> nums = allNum();
				String[] headerRecord = {"Id", "OriginalNumber", "Number", "Incorrect", "Modified", "Edit"};
				csvWriter.writeNext(headerRecord);
				for (TelNum telNum : nums) {
					String[] entry = {""+telNum.getId(), telNum.getOriginalNum(), telNum.getNum(), ""+telNum.isIncorrect(), ""+telNum.isModified(), telNum.getEdit()};
					csvWriter.writeNext(entry);
				}
			}
            return p;
        } catch (Exception e) {
            System.out.println(e);
        }
		return null;
	}
	
}
