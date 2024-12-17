package com.gachirex.fileSorter.controller;

import com.gachirex.fileSorter.dto.MediaRequest;
import com.gachirex.fileSorter.dto.MediaResponse;
import com.gachirex.fileSorter.model.Media;
import com.gachirex.fileSorter.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : lämmchen
 * @mailto : tokotuulamm.L@gmail.com
 * @created : 17/12/2024
 **/
@CrossOrigin(origins = "moz-extension://26543a28-208d-492e-8786-d59b21762ca6/manifest.json")
@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;
    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);

    @PostMapping
    public MediaResponse addMedia(@RequestBody MediaRequest request) throws Exception {
        Media media = mediaService.addMedia(
                request.getName(),
                request.getLabels(),
                request.getCollection(),
                request.getSourceUrl()
        );

        return convertToResponse(media);
    }

    @GetMapping("/{id}")
    public MediaResponse getMedia(@PathVariable Long id) {
        logger.info("GET request received for ID: {}", id);
        Media media = mediaService.getMediaById(id);
        return convertToResponse(media);
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> getMediaFile(@PathVariable Long id) {
        byte[] fileData = mediaService.getMediaFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("video/webm")) // Static MIME type
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"media-" + id + ".webm\"")
                .body(fileData);
    }


    @DeleteMapping("/{id}/")
    public void deleteMedia(@PathVariable Long id) {
        logger.info("DELETE request received for ID: {}", id);
        mediaService.deleteMedia(id);
    }

    private MediaResponse convertToResponse(Media media) {
        MediaResponse response = new MediaResponse();
        response.setId(media.getId());
        response.setName(media.getName());
        response.setLabels(media.getLabels());
        response.setCollection(media.getCollection());
        response.setAddedDate(media.getAddedDate());
        return response;
    }
}
