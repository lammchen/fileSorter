package com.gachirex.fileSorter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author : lämmchen
 * @mailto : tokotuulamm.L@gmail.com
 * @created : 17/12/2024
 **/
@Entity
@Table(name="media")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Column(name = "file_data", columnDefinition = "LONGBLOB")
    private byte[] fileData;

    private String labels;      // Are to be comma-separated
    private String collection;

    @Builder.Default
    private LocalDateTime addedDate = LocalDateTime.now();
}
