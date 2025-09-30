package com.jasoncordova.waylink.service;

import com.jasoncordova.waylink.data.entity.RecordEntity;
import com.jasoncordova.waylink.data.entity.UrlEntity;
import com.jasoncordova.waylink.data.repository.UrlRepository;
import com.jasoncordova.waylink.web.model.UrlModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    private UrlEntity UrlModelToEntity(UrlModel urlModel) {

        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setId(urlModel.getId());
        urlEntity.setShortUrl(urlModel.getShortUrl());
        urlEntity.setLongUrl(urlModel.getLongUrl());
        urlEntity.setCreatedAt(urlModel.getCreatedOn());
        return urlEntity;

    }

    private UrlModel UrlEntityToModel(UrlEntity urlEntity) {

        UrlModel urlModel = new UrlModel();
        urlModel.setShortUrl(urlEntity.getShortUrl());
        urlModel.setLongUrl(urlEntity.getLongUrl());
        urlModel.setId(urlEntity.getId());
        urlModel.setCreatedOn(urlEntity.getCreatedAt());
        return urlModel;

    }

    public List<UrlModel> getAll() {
        return urlRepository.findAll().stream().map(this::UrlEntityToModel).toList();
    }

    public UrlModel getUrlById(UUID id){

        return urlRepository.findById(id)
                .map(this::UrlEntityToModel)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public UrlModel getUrlByShortUrl(String shortUrl){

        return urlRepository.findByShortUrl(shortUrl)
                .map(this::UrlEntityToModel)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public UrlModel addUrl(UrlModel urlModel){

        UrlEntity urlEntity = UrlModelToEntity(urlModel);
        urlEntity = this.urlRepository.save(urlEntity);
        return this.UrlEntityToModel(urlEntity);

    }

    public UrlModel updateUrl(UrlModel urlModel){

        UrlEntity urlEntity = UrlModelToEntity(urlModel);
        urlEntity = this.urlRepository.save(urlEntity);
        return this.UrlEntityToModel(urlEntity);

    }

    public void deleteUrlById(UUID id){
        urlRepository.deleteById(id);
    }

}
