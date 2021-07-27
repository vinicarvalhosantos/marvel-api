package com.vinicius.santos.marvelapi.repository;

import com.vinicius.santos.marvelapi.model.entity.ComicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComicRepository extends JpaRepository<ComicEntity, Long> {

    @Query(value = "SELECT mc.id, mc.digital_id, mc.title, mc.issue_number, mc.variant_description, mc.description, mc.modified\n" +
            "FROM marvel_comics mc \n" +
            "INNER JOIN marvel_characters_comics mcc ON mcc.comics_id = mc.id\n" +
            "WHERE mcc.marvel_characters_id =?1", nativeQuery = true)
    List<ComicEntity> findComicsByCharacter(Long id);

}
