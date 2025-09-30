package com.jasoncordova.waylink.api;

import com.jasoncordova.waylink.service.UrlService;
import com.jasoncordova.waylink.web.model.UrlModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/urls")
public class UrlRestController {

    private final UrlService urlService;

    public UrlRestController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping
    public List<UrlModel> getAllUrls() {
        return urlService.getAll();
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<UrlModel> getUrl(@PathVariable String shortUrl) {
        UrlModel urlModel = urlService.getUrlByShortUrl(shortUrl);
        return (urlModel != null) ? ResponseEntity.ok(urlModel) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UrlModel> createUrl(@RequestBody UrlModel request) {
        UrlModel created = urlService.addUrl(request);
        return ResponseEntity
                .created(URI.create("/api/urls/" + created.getShortUrl()))
                .body(created);
    }

    @DeleteMapping("/{shortUrl}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortUrl) {
        boolean deleted = urlService.deleteUrlByShortUrl(shortUrl);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
