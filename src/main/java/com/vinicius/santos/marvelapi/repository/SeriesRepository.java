package com.vinicius.santos.marvelapi.repository;

import com.vinicius.santos.marvelapi.model.entity.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<SeriesEntity, Long> {

    @Query(value = "SELECT ms.id, ms.title, ms.description\n" +
            "FROM marvel_series ms \n" +
            "INNER JOIN marvel_characters_series mcs ON ms.id = mcs.series_id \n" +
            "WHERE mcs.marvel_characters_id =?1", nativeQuery = true)
    List<SeriesEntity> findSeriesByCharacter(Long id);

}
