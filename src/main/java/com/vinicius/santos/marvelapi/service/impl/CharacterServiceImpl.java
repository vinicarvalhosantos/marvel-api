package com.vinicius.santos.marvelapi.service.impl;

import com.vinicius.santos.marvelapi.model.entity.*;
import com.vinicius.santos.marvelapi.repository.*;
import com.vinicius.santos.marvelapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ComicRepository comicRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public List<CharacterEntity> getAllCharacters() {

        List<CharacterEntity> characterList = characterRepository.findAll();

        if (characterList.isEmpty()) return null;
        return characterList;

    }

    @Override
    public Optional<CharacterEntity> getCharacterById(Long id) {

        return characterRepository.findById(id);

    }

    @Override
    public List<ComicEntity> getComicsByCharacterId(Long id) {

        List<ComicEntity> comicList = comicRepository.findComicsByCharacter(id);

        if(comicList.isEmpty()) return null;
        return comicList;

    }

    @Override
    public List<EventEntity> getEventsByCharacterId(Long id) {
        List<EventEntity> eventList = eventRepository.findEventsByCharacter(id);

        if(eventList.isEmpty()) return null;
        return eventList;
    }

    @Override
    public List<SeriesEntity> getSeriesByCharacterId(Long id) {
        List<SeriesEntity> seriesList = seriesRepository.findSeriesByCharacter(id);

        if(seriesList.isEmpty()) return null;
        return seriesList;
    }

    @Override
    public List<StoryEntity> getStoriesByCharacterId(Long id) {
        List<StoryEntity> storiesList = storyRepository.findStoriesByCharacter(id);

        if(storiesList.isEmpty()) return null;
        return storiesList;
    }

}
