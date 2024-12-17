package com.gachirex.fileSorter.repository;

import com.gachirex.fileSorter.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : lämmchen
 * @mailto : tokotuulamm.L@gmail.com
 * @created : 17/12/2024
**/
public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByLabelsContaining(String label);
    List<Media> findByCollection(String collection);
}
