package com.jasoncordova.waylink.api;

import com.jasoncordova.waylink.service.RecordService;
import com.jasoncordova.waylink.service.UrlService;
import com.jasoncordova.waylink.web.model.RecordModel;
import com.jasoncordova.waylink.web.model.UrlModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/records")
public class RecordRestController {

    private final RecordService recordService;
    private final UrlService urlService;

    public RecordRestController(RecordService recordService, UrlService urlService) {
        this.recordService = recordService;
        this.urlService = urlService;
    }

    @GetMapping
    public List<RecordModel> getAllRecords() {
        return recordService.getAllRecords();
    }

    @GetMapping("/{shortUrl}")
    public Map<String, Object> getRecordsByShortUrl(@PathVariable String shortUrl) {
        List<RecordModel> recordModels = recordService.getRecordsByShortUrl(shortUrl);
        UrlModel urlModel = urlService.getUrlByShortUrl(shortUrl);

        return Map.of(
                "shortUrl", urlModel.getShortUrl(),
                "longUrl", urlModel.getLongUrl(),
                "records", recordModels,
                "recordAmount", recordModels.size()
        );
    }

}
