package com.vinicius.santos.marvelapi.test;

import com.vinicius.santos.marvelapi.model.entity.*;
import com.vinicius.santos.marvelapi.repository.*;
import com.vinicius.santos.marvelapi.service.CharacterService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CharacterService.class)
public class CharacterServiceTest {

    @Autowired
    CharacterService characterService;

    @MockBean
    CharacterRepository characterRepository;

    @MockBean
    ComicRepository comicRepository;

    @MockBean
    EventRepository eventRepository;

    @MockBean
    SeriesRepository seriesRepository;

    @MockBean
    StoryRepository storyRepository;

    @Test
    public void it_should_get_all_characters() {
        CharacterEntity characterEntity = new CharacterEntity();
        List<CharacterEntity> characterList = new ArrayList<>();
        characterEntity.setId(1548L);
        characterEntity.setName("Name Test");
        characterEntity.setDescription("Description Test");
        characterList.add(characterEntity);

        when(characterRepository.findAll()).thenReturn(characterList);

        List<CharacterEntity> characterResponse = characterService.getAllCharacters();

        Assert.assertNotNull(characterResponse);

    }

    @Test
    public void it_should_not_found_on_get_all_characters() {

        when(characterRepository.findAll()).thenReturn(new ArrayList<>());

        List<CharacterEntity> characterResponse = characterService.getAllCharacters();

        Assert.assertNull(characterResponse);

    }

    @Test
    public void it_should_bring_character_by_id() {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setId(1548L);
        characterEntity.setName("Name Test");
        characterEntity.setDescription("Description Test");
        Optional<CharacterEntity> optionalCharacter = Optional.of(characterEntity);

        when(characterRepository.findById(any(Long.class))).thenReturn(optionalCharacter);

        Optional<CharacterEntity> characterResponse = characterService.getCharacterById(any(Long.class));

        Assert.assertTrue(characterResponse.isPresent());

    }

    @Test
    public void it_should_not_found_on_bring_character_by_id() {
        Optional<CharacterEntity> optionalCharacter = Optional.empty();

        when(characterRepository.findById(any(Long.class))).thenReturn(optionalCharacter);

        Optional<CharacterEntity> characterResponse = characterService.getCharacterById(any(Long.class));

        Assert.assertFalse(characterResponse.isPresent());

    }

    @Test
    public void it_should_get_comics_by_characters() {
        ComicEntity comicEntity = new ComicEntity();
        List<ComicEntity> comicList = new ArrayList<>();
        comicEntity.setId(1548L);
        comicEntity.setDigitalId(445L);
        comicEntity.setTitle("Title Tests");
        comicEntity.setVariantDescription("Variant Description Test");
        comicEntity.setIssueNumber(10);
        comicEntity.setDescription("Description Test");
        comicList.add(comicEntity);

        when(comicRepository.findComicsByCharacter(any(Long.class))).thenReturn(comicList);

        List<ComicEntity> comicsResponse = characterService.getComicsByCharacterId(any(Long.class));

        Assert.assertNotNull(comicsResponse);

    }

    @Test
    public void it_should_not_found_on_get_comics_by_character() {

        when(comicRepository.findComicsByCharacter(any(Long.class))).thenReturn(new ArrayList<>());

        List<ComicEntity> comicsResponse = characterService.getComicsByCharacterId(any(Long.class));

        Assert.assertNull(comicsResponse);

    }

    @Test
    public void it_should_get_series_by_characters() {
        SeriesEntity seriesEntity = new SeriesEntity();
        List<SeriesEntity> seriesList = new ArrayList<>();
        seriesEntity.setId(1548L);
        seriesEntity.setTitle("Title Tests");
        seriesEntity.setDescription("Description Test");
        seriesList.add(seriesEntity);

        when(seriesRepository.findSeriesByCharacter(any(Long.class))).thenReturn(seriesList);

        List<SeriesEntity> seriesResponse = characterService.getSeriesByCharacterId(any(Long.class));

        Assert.assertNotNull(seriesResponse);

    }

    @Test
    public void it_should_not_found_on_get_series_by_character() {

        when(seriesRepository.findSeriesByCharacter(any(Long.class))).thenReturn(new ArrayList<>());

        List<SeriesEntity> seriesResponse = characterService.getSeriesByCharacterId(any(Long.class));

        Assert.assertNull(seriesResponse);

    }

    @Test
    public void it_should_get_stories_by_characters() {
        StoryEntity storyEntity = new StoryEntity();
        List<StoryEntity> storyList = new ArrayList<>();
        storyEntity.setId(1548L);
        storyEntity.setTitle("Title Tests");
        storyEntity.setDescription("Description Test");
        storyEntity.setType("Type tests");
        storyList.add(storyEntity);

        when(storyRepository.findStoriesByCharacter(any(Long.class))).thenReturn(storyList);

        List<StoryEntity> storiesResponse = characterService.getStoriesByCharacterId(any(Long.class));

        Assert.assertNotNull(storiesResponse);

    }

    @Test
    public void it_should_not_found_on_get_stories_by_character() {

        when(storyRepository.findStoriesByCharacter(any(Long.class))).thenReturn(new ArrayList<>());

        List<StoryEntity> storiesResponse = characterService.getStoriesByCharacterId(any(Long.class));

        Assert.assertNull(storiesResponse);

    }

    @Test
    public void it_should_get_events_by_characters() {
        EventEntity eventEntity = new EventEntity();
        List<EventEntity> eventList = new ArrayList<>();
        eventEntity.setId(1548L);
        eventEntity.setTitle("Title Tests");
        eventEntity.setDescription("Description Test");
        eventList.add(eventEntity);

        when(eventRepository.findEventsByCharacter(any(Long.class))).thenReturn(eventList);

        List<EventEntity> eventsResponse = characterService.getEventsByCharacterId(any(Long.class));

        Assert.assertNotNull(eventsResponse);

    }

    @Test
    public void it_should_not_found_on_get_events_by_character() {

        when(eventRepository.findEventsByCharacter(any(Long.class))).thenReturn(new ArrayList<>());

        List<EventEntity> eventsResponse = characterService.getEventsByCharacterId(any(Long.class));

        Assert.assertNull(eventsResponse);

    }

}
