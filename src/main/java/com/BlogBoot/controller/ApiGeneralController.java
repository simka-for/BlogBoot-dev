package com.BlogBoot.controller;

import com.BlogBoot.responses.InitApiResponse;
import com.BlogBoot.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {

    @Autowired
    GeneralService generalService;

    @GetMapping("/init")
    @ResponseStatus(HttpStatus.OK)
    public InitApiResponse generalBlogData(){
        return generalService.generalBlogData();
    }
}
