package de.mle.stackoverflow.jackson;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class JsonController {
    @GetMapping(path = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getJson() {
        return "{ \"singleItemList\" : 3 }";
    }
}
