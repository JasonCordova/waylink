package com.jasoncordova.waylink.web.controller;

import com.jasoncordova.waylink.service.UrlService;
import com.jasoncordova.waylink.web.model.RecordModel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UrlService urlService;

    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate, UrlService urlService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.urlService = urlService;
    }

    public void broadcastRecord(RecordModel recordModel){

        String shortUrl = urlService.getUrlById(recordModel.getUrlId()).getShortUrl();

        simpMessagingTemplate.convertAndSend("/topic/" + shortUrl, recordModel);

    }

}
