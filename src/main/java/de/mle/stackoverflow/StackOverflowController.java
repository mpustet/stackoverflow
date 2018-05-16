package de.mle.stackoverflow;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StackOverflowController {
	private static final String JSON_FILE = StackOverflowController.class.getResource("/jsonFile.json").getFile();

	@GetMapping("/jsonFile")
	public String getJsonFile() throws IOException {
		return FileUtils.readFileToString(new File(JSON_FILE), "UTF-8");
	}

	@GetMapping("/link")
	public ResponseEntity<String> link(@RequestParam(required = false) String param) throws IOException {
		return ResponseEntity.ok(param);
	}
}
