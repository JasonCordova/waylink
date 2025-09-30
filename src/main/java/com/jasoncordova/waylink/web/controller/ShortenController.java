package com.jasoncordova.waylink.web.controller;

import com.jasoncordova.waylink.service.RecordService;
import com.jasoncordova.waylink.service.UrlService;
import com.jasoncordova.waylink.web.model.UrlModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/shorten")
public class ShortenController {

    private final UrlService urlService;
    private final RecordService recordService;

    public ShortenController(UrlService urlService, RecordService recordService) {
        this.urlService = urlService;
        this.recordService = recordService;
    }

    @GetMapping
    public String shortenPage(){

        return "shorten";

    }

    @PostMapping
    public String shorten(@RequestParam("url") String url){

        String urlPattern = "^(https?:\\/\\/)?(www\\.)?([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}(\\/[^\s]*)?$";

        if (!url.matches(urlPattern)) {
            System.out.println("Invalid URL: " + url);
            return "redirect:/shorten"; // or show a message
        }

        UrlModel urlModel = new UrlModel();

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            urlModel.setLongUrl("http://" + url);
        } else urlModel.setLongUrl(url);
        String tempShort = generateShortCode(); // generate your short URL code
        urlModel.setShortUrl(tempShort);
        urlModel.setCreatedOn(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

        urlService.addUrl(urlModel);
        System.out.println("URL: " + url);

        return "redirect:/" + tempShort + "/stats";

    }

    private String generateShortCode() {
        // Simple short URL generator: 6 random alphanumeric characters
        return UUID.randomUUID().toString().substring(0, 6);
    }

}
