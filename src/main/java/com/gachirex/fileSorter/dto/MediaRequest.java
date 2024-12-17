package com.gachirex.fileSorter.dto;

import lombok.Data;

/**
 * @author : lämmchen
 * @mailto : tokotuulamm.L@gmail.com
 * @created : 17/12/2024
 **/
@Data
public class MediaRequest {
    private String name;
    private String labels;
    private String collection;
    private String sourceUrl;
}
