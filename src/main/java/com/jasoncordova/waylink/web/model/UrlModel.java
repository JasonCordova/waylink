package com.jasoncordova.waylink.web.model;


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
public class UrlModel {

    private UUID id;
    private String shortUrl;
    private String longUrl;
    private LocalDateTime createdOn;

}
