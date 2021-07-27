package com.vinicius.santos.marvelapi.service;

import com.vinicius.santos.marvelapi.model.entity.*;

import java.util.List;
import java.util.Optional;

public interface CharacterService {

    List<CharacterEntity> getAllCharacters();

    Optional<CharacterEntity> getCharacterById(Long id);

    List<ComicEntity> getComicsByCharacterId(Long id);

    List<EventEntity> getEventsByCharacterId(Long id);

    List<SeriesEntity> getSeriesByCharacterId(Long id);

    List<StoryEntity> getStoriesByCharacterId(Long id);

}
