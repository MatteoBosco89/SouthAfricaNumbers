package controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import model.TelNum;
import service.Branch;

@RestController
public class SouthAfricaNumController implements SouthAfricaNumApi{
	
	@Autowired
	private Branch branch;
	
	@Override
	public ResponseEntity<Object> listNum() {
		return ResponseEntity.status(200).body(branch.allNum());
	}

	@Override
	public ResponseEntity<Object> checkSingleNum(String num) {
		TelNum n = branch.SingleNumParser(num);
		return ResponseEntity.status(200).body(n);
	}

	@Override
	public ResponseEntity<Resource> downloadCSV() {
		HttpHeaders header = new HttpHeaders();
		ByteArrayResource resource = null;
		File file = null;
		try {
			Path tmp = branch.tempFile();
	        file = tmp.toFile();
	        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=NumList.csv");
	        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
	        header.add("Pragma", "no-cache");
	        header.add("Expires", "0");
	        resource = new ByteArrayResource(Files.readAllBytes(tmp));
		} catch (Exception e) { System.out.println(e); }    
		return ResponseEntity.status(200).headers(header).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
    }

	@Override
	public ResponseEntity<String> checkCSV(MultipartFile file) {
		try {
			branch.CSVParser(file.getInputStream());
		} catch (Exception e) { System.out.println(e); }
		return ResponseEntity.status(200).body(file.getOriginalFilename());
	}
		
}
