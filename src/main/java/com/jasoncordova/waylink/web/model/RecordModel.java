package com.jasoncordova.waylink.web.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordModel {

    private UUID id;
    private UUID urlId;
    private LocalDateTime clickedAt;
    private String timeZone;
    private String device;
    private String os;

}
