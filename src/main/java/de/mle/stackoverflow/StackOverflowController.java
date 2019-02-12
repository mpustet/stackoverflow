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

	@GetMapping("/deserialize")
	public ResponseEntity<String> link() {
		String content = "{\"projectId\":9,\"workspaceId\":74,\"projectName\":\"Test Project 1dea0d3e-4bba-4cc2-ace3-77dede4990d5\",\"phases\":[],\"estimateType\":{\"name\":\"DAY\",\"displayName\":\"Days\",\"id\":1}}";
		return ResponseEntity.ok(content);
	}
}
