package com.gachirex.fileSorter.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author : lämmchen
 * @mailto : tokotuulamm.L@gmail.com
 * @created : 17/12/2024
 **/
@Data
public class MediaResponse {
    private Long id;
    private String name;
    private String labels;
    private String collection;
    private LocalDateTime addedDate;
}
