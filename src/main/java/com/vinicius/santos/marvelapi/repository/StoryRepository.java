package com.vinicius.santos.marvelapi.repository;

import com.vinicius.santos.marvelapi.model.entity.SeriesEntity;
import com.vinicius.santos.marvelapi.model.entity.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryRepository extends JpaRepository<StoryEntity, Long> {

    @Query(value = "SELECT ms2.id, ms2.title, ms2.description, ms2.`type`, ms2.modified\n" +
            "FROM marvel_stories ms2 \n" +
            "INNER JOIN marvel_characters_stories mcs2 ON ms2.id = mcs2.stories_id \n" +
            "WHERE mcs2.marvel_characters_id = ?1", nativeQuery = true)
    List<StoryEntity> findStoriesByCharacter(Long id);

}
