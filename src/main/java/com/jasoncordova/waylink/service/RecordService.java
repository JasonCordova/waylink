package com.jasoncordova.waylink.service;

import com.jasoncordova.waylink.data.entity.RecordEntity;
import com.jasoncordova.waylink.data.repository.RecordRepository;
import com.jasoncordova.waylink.data.repository.UrlRepository;
import com.jasoncordova.waylink.web.model.RecordModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RecordService {

    private final RecordRepository recordRepository;
    private final UrlRepository urlRepository;

    public RecordService(RecordRepository recordRepository, UrlRepository urlRepository) {
        this.recordRepository = recordRepository;
        this.urlRepository = urlRepository;
    }

    private RecordModel RecordEntityToModel(RecordEntity recordEntity){

        RecordModel recordModel = new RecordModel();
        recordModel.setId(recordEntity.getId());
        recordModel.setUrlId(recordEntity.getUrlId());
        recordModel.setClickedAt(recordEntity.getClicked_at());
        recordModel.setTimeZone(recordEntity.getTimezone());
        recordModel.setOs(recordEntity.getOs());
        recordModel.setDevice(recordEntity.getDevice());
        return recordModel;

    }

    private RecordEntity RecordModelToEntity(RecordModel recordModel){
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setId(recordModel.getId());
        recordEntity.setUrlId(recordModel.getUrlId());
        recordEntity.setOs(recordModel.getOs());
        recordEntity.setDevice(recordModel.getDevice());
        recordEntity.setTimezone(recordModel.getTimeZone());
        recordEntity.setClicked_at(recordModel.getClickedAt());
        return recordEntity;
    }

    public List<RecordModel> getAllRecords(){

        return recordRepository.findAll()
                .stream()
                .map(this::RecordEntityToModel)
                .toList();

    }

    public RecordModel addRecord(RecordModel recordModel){

        RecordEntity recordEntity = RecordModelToEntity(recordModel);
        recordRepository.save(recordEntity);
        return this.RecordEntityToModel(recordEntity);

    }

    public RecordModel updateRecord(RecordModel recordModel){

        RecordEntity recordEntity = RecordModelToEntity(recordModel);
        recordRepository.save(recordEntity);
        return this.RecordEntityToModel(recordEntity);

    }

    private void deleteRecord(UUID id){
        recordRepository.deleteById(id);
    }

    public List<RecordModel> getRecordsById(UUID urlId){

        List<RecordModel> records = recordRepository.findByUrlId(urlId)
                .stream().map(this::RecordEntityToModel).toList();

//        if (records.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return records;

    }

    public List<RecordModel> getRecordsByShortUrl(String shortUrl){

        UUID urlId = urlRepository.findByShortUrl(shortUrl)
                .map(urlEntity -> urlEntity.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return getRecordsById(urlId);

    }

}
