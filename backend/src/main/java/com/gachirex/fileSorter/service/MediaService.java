package com.gachirex.fileSorter.service;

import com.gachirex.fileSorter.model.Media;
import com.gachirex.fileSorter.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author : lämmchen
 * @mailto : tokotuulamm.L@gmail.com
 * @created : 17/12/2024
 **/
@Service
@RequiredArgsConstructor
public class MediaService {

    private final MediaRepository mediaRepository;

    /**
     * Downloads a media from its source URL and stores it into the database
     * @param name
     * @param labels
     * @param collection
     * @param sourceUrl
     * @return
     * @throws Exception
     */
    public Media addMedia(String name, String labels, String collection, String sourceUrl) throws Exception {
        URL url = new URL(sourceUrl);
        try (InputStream inputStream = url.openStream()) {
            byte[] fileData = inputStream.readAllBytes(); // Récupère les données binaires

            Media media = Media.builder()
                    .name((name != null) ? name : url.getPath().substring(url.getPath().lastIndexOf('/') + 1))
                    .fileData(fileData)
                    .labels(labels)
                    .collection((collection != null) ? collection : "default")
                    .build();

            return mediaRepository.save(media);
        }
    }

    /**
     * Retrieves a media by its ID (without file data).
     * @param id
     * @return
     */
    public Media getMediaById(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found with id: " + id));
    }

    /**
     * Retrieves only the binary data of the media.
     * @param id
     * @return
     */
    public byte[] getMediaFile(Long id) {
        Media media = getMediaById(id);
        return media.getFileData();
    }

    /**
     * Deletes a media by its ID.
     * @param id
     */
    public void deleteMedia(Long id) {
        mediaRepository.deleteById(id);
    }
}