package com.vinicius.santos.marvelapi.repository;

import com.vinicius.santos.marvelapi.model.entity.ComicEntity;
import com.vinicius.santos.marvelapi.model.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    @Query(value = "SELECT me.id, me.title, me.description\n" +
            "FROM marvel_events me \n" +
            "INNER JOIN marvel_characters_events mce ON me.id = mce.events_id\n" +
            "WHERE mce.marvel_characters_id =?1", nativeQuery = true)
    List<EventEntity> findEventsByCharacter(Long id);

}
