package com.jasoncordova.waylink.web.controller;

import com.jasoncordova.waylink.data.entity.UrlEntity;
import com.jasoncordova.waylink.data.repository.UrlRepository;
import com.jasoncordova.waylink.service.RecordService;
import com.jasoncordova.waylink.service.UrlService;
import com.jasoncordova.waylink.web.model.RecordModel;
import com.jasoncordova.waylink.web.model.UrlModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class UrlController {

    private final UrlService urlService;
    private final RecordService recordService;
    private final WebSocketController webSocketController;

    public UrlController(UrlService urlService, RecordService recordService, WebSocketController webSocketController) {
        this.urlService = urlService;
        this.recordService = recordService;
        this.webSocketController = webSocketController;
    }
    
    @GetMapping("/urls")
    public String getUrls(Model model){

        List<UrlModel> urlModels = urlService.getAll();
        model.addAttribute("urls", urlModels);
        return "urls";
        
    }

    @GetMapping("/{shortUrl}")
    public String getUrlByShortUrl(@PathVariable String shortUrl, Model model){

        UrlModel urlModel = urlService.getUrlByShortUrl(shortUrl);
        model.addAttribute("url", urlModel);
        return "redirect";

    }

    @PostMapping("/test")
    @ResponseBody
    public ResponseEntity<Void> track(@RequestBody RecordModel record){

        if (record.getUrlId() == null ||
                record.getTimeZone() == null || record.getTimeZone().isEmpty() ||
                record.getClickedAt() == null ||
                record.getDevice() == null || record.getDevice().isEmpty() ||
                record.getOs() == null || record.getOs().isEmpty()) {

            System.err.println("❌ Missing required fields in record: " + record);
            return ResponseEntity.badRequest().build();
        }

        recordService.addRecord(record);
        webSocketController.broadcastRecord(record);
        return ResponseEntity.ok().build();

    }

    
}
