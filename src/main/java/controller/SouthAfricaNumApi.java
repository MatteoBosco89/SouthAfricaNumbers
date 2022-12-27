package controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


public interface SouthAfricaNumApi {
	
	@GetMapping("/listNum")
	public ResponseEntity<Object> listNum();
	
	@GetMapping("/downloadCSV")
	public ResponseEntity<Resource> downloadCSV();
	
	@PostMapping("/checkNum")
	public ResponseEntity<Object> checkSingleNum(@RequestParam("number") String num);
	
	@PostMapping("/checkCSV")
	public ResponseEntity<String> checkCSV(@RequestParam("up-file-input[]") MultipartFile file);
}
