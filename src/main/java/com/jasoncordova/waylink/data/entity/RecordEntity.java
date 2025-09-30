package com.jasoncordova.waylink.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="RECORDS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="url_id")
    private UUID urlId;

    @Column(name="clicked_at")
    private LocalDateTime clicked_at;

    @Column(name="timezone")
    private String timezone;

    @Column(name="device")
    private String device;

    @Column(name="os")
    private String os;

    @Override
    public String toString(){
        return "Record(" + id + ") -> URL(" + urlId + ") [" + clicked_at + " / " + timezone + "]";
    }

}
