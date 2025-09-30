package com.jasoncordova.waylink.web.controller;

import com.jasoncordova.waylink.service.RecordService;
import com.jasoncordova.waylink.service.UrlService;
import com.jasoncordova.waylink.web.model.RecordModel;
import com.jasoncordova.waylink.web.model.UrlModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping()
public class RecordController {

    private final RecordService recordService;
    private final UrlService urlService;

    public RecordController(RecordService recordService, UrlService urlService) {
        this.recordService = recordService;
        this.urlService = urlService;
    }


    @GetMapping("/{shortUrl}/stats")
    public String getRecordByUrl(@PathVariable String shortUrl, Model model) {
        List<RecordModel> recordModels = recordService.getRecordsByShortUrl(shortUrl);
        System.out.println("Checking stats for " + shortUrl);
        UrlModel urlModel = urlService.getUrlByShortUrl(shortUrl);

        model.addAttribute("shortUrl",  urlModel.getShortUrl());
        model.addAttribute("longUrl", urlModel.getLongUrl());
        model.addAttribute("records", recordModels);
        model.addAttribute("recordAmount", recordModels.size());

        return "custom-stats";
    }

    @GetMapping("/stats")
    public String getRecords(Model model) {

        List<RecordModel> recordModels = recordService.getAllRecords();
        model.addAttribute("records", recordModels);
        return "stats";

    }

}
