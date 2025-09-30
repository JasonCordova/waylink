package com.jasoncordova.waylink.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="URLS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="short_url")
    private String shortUrl;

    @Column(name="long_url")
    private String longUrl;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Override
    public String toString(){

        return "URL(" + id + ") maps " + shortUrl + " -> " + longUrl + "[" + createdAt + "]";

    }

}
