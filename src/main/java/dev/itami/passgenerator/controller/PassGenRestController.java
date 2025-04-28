package dev.itami.passgenerator.controller;

import dev.itami.passgenerator.model.GeneratorOptions;
import dev.itami.passgenerator.service.PassGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PassGenRestController {
    @Autowired
    private PassGenService service;

    @PostMapping("/generate")
    private String generatePassword(@RequestBody GeneratorOptions options) {
        return service.generatePassword(options.getLength(), options.isUseNumbers(), options.isUseUppercase(), options.isUseSymbols());
    }

}
