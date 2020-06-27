package com.BlogBoot.service;

import com.BlogBoot.responses.InitApiResponse;
import org.springframework.stereotype.Service;

@Service
public class GeneralService {

    public InitApiResponse generalBlogData() {

        return new InitApiResponse(
                "devPub",
                "Рассказы разработчиков",
                "+7 915 404 6772",
                "simka.for@gmail.com",
                "Обрезумов Серафим",
                "2020");
    }
}
