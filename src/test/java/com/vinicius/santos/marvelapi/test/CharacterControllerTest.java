package com.vinicius.santos.marvelapi.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinicius.santos.marvelapi.controller.v1.CharacterController;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {

    @MockBean
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

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void it_should_bring_all_characters() throws Exception {

        CharacterEntity characterEntity = new CharacterEntity();
        List<CharacterEntity> characterList = new ArrayList<>();
        characterEntity.setId(1548L);
        characterEntity.setName("Name Test");
        characterEntity.setDescription("Description Test");
        characterList.add(characterEntity);


        when(characterService.getAllCharacters()).thenReturn(characterList);

        when(characterRepository.findAll()).thenReturn(characterList);

        List<CharacterEntity> characterResponse = characterService.getAllCharacters();

        Assert.assertNotNull(characterResponse);

        mockMvc.perform(get("/v1/public/characters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.copyright").value("© 2021 MARVEL"))
                .andExpect(jsonPath("$.attributionText").value("Data provided by Marvel. © 2021 MARVEL"))
                .andExpect(jsonPath("$.attributionHTML").value("<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2021 MARVEL</a>"))
                .andExpect(jsonPath("$.etag").value("55342c8b21941bfea4b795ff85633d9063e1da0e"))
                .andExpect(jsonPath("$.data.result[0][0].id").value(1548))
                .andExpect(jsonPath("$.data.result[0][0].name").value("Name Test"))
                .andExpect(jsonPath("$.data.result[0][0].description").value("Description Test"));

    }

    @Test
    public void it_should_not_found_any_characters() throws Exception {

        when(characterService.getAllCharacters()).thenReturn(null);

        when(characterRepository.findAll()).thenReturn(null);

        List<CharacterEntity> characterResponse = characterService.getAllCharacters();

        Assert.assertNull(characterResponse);

        mockMvc.perform(get("/v1/public/characters"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.status").value("We couldnt find any characters"));

    }

    @Test
    public void it_should_bring_character_by_id() throws Exception {

        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setId(1548L);
        characterEntity.setName("Name Test");
        characterEntity.setDescription("Description Test");

        Optional<CharacterEntity> optionalCharacter = Optional.of(characterEntity);


        when(characterService.getCharacterById(any(Long.class))).thenReturn(optionalCharacter);

        when(characterRepository.findById(any(Long.class))).thenReturn(optionalCharacter);

        Optional<CharacterEntity> characterResponse = characterService.getCharacterById(characterEntity.getId());

        Assert.assertNotNull(characterResponse);

        mockMvc.perform(get("/v1/public/characters/1548"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.copyright").value("© 2021 MARVEL"))
                .andExpect(jsonPath("$.attributionText").value("Data provided by Marvel. © 2021 MARVEL"))
                .andExpect(jsonPath("$.attributionHTML").value("<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2021 MARVEL</a>"))
                .andExpect(jsonPath("$.etag").value("55342c8b21941bfea4b795ff85633d9063e1da0e"))
                .andExpect(jsonPath("$.data.result[0].id").value(1548))
                .andExpect(jsonPath("$.data.result[0].name").value("Name Test"))
                .andExpect(jsonPath("$.data.result[0].description").value("Description Test"));

    }

    @Test
    public void it_should_not_found_character_by_id() throws Exception {

        when(characterService.getCharacterById(any(Long.class))).thenReturn(Optional.empty());

        when(characterRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        Optional<CharacterEntity> characterResponse = characterService.getCharacterById(any(Long.class));

        Assert.assertSame(Optional.empty(), characterResponse);

        mockMvc.perform(get("/v1/public/characters/15481"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.status").value("We couldnt find that character"));
    }

    @Test
    public void it_should_bring_comics_by_character() throws Exception {

        List<ComicEntity> comicEntityList = new ArrayList<>();
        ComicEntity comicEntity = new ComicEntity();
        comicEntity.setId(144L);
        comicEntity.setDigitalId(445L);
        comicEntity.setIssueNumber(8);
        comicEntity.setTitle("Title test");
        comicEntity.setDescription("Description Test");
        comicEntity.setVariantDescription("Variant Description Test");

        comicEntityList.add(comicEntity);

        comicEntity = new ComicEntity();
        comicEntity.setId(877L);
        comicEntity.setDigitalId(87L);
        comicEntity.setIssueNumber(74);
        comicEntity.setTitle("Title test");
        comicEntity.setDescription("Description Test");
        comicEntity.setVariantDescription("Variant Description Test");

        comicEntityList.add(comicEntity);

        when(characterService.getComicsByCharacterId(any(Long.class))).thenReturn(comicEntityList);

        when(comicRepository.findComicsByCharacter(any(Long.class))).thenReturn(comicEntityList);

        List<ComicEntity> comicsCharacterResponse = characterService.getComicsByCharacterId(any(Long.class));

        Assert.assertNotNull(comicsCharacterResponse);

        mockMvc.perform(get("/v1/public/characters/1548/comics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.copyright").value("© 2021 MARVEL"))
                .andExpect(jsonPath("$.attributionText").value("Data provided by Marvel. © 2021 MARVEL"))
                .andExpect(jsonPath("$.attributionHTML").value("<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2021 MARVEL</a>"))
                .andExpect(jsonPath("$.etag").value("55342c8b21941bfea4b795ff85633d9063e1da0e"))
                .andExpect(jsonPath("$.data.result[0][1].id").value(877))
                .andExpect(jsonPath("$.data.result[0][1].digitalId").value(87))
                .andExpect(jsonPath("$.data.result[0][1].issueNumber").value(74))
                .andExpect(jsonPath("$.data.result[0][1].title").value("Title test"))
                .andExpect(jsonPath("$.data.result[0][1].description").value("Description Test"))
                .andExpect(jsonPath("$.data.result[0][1].variantDescription").value("Variant Description Test"))
                .andExpect(jsonPath("$.data.result[0][0].id").value(144))
                .andExpect(jsonPath("$.data.result[0][0].digitalId").value(445))
                .andExpect(jsonPath("$.data.result[0][0].issueNumber").value(8))
                .andExpect(jsonPath("$.data.result[0][0].title").value("Title test"))
                .andExpect(jsonPath("$.data.result[0][0].description").value("Description Test"))
                .andExpect(jsonPath("$.data.result[0][0].variantDescription").value("Variant Description Test"));
    }

    @Test
    public void it_should_not_found_comics_by_character() throws Exception {

        when(characterService.getComicsByCharacterId(any(Long.class))).thenReturn(null);

        when(comicRepository.findComicsByCharacter(any(Long.class))).thenReturn(null);

        List<ComicEntity> comicsCharacterResponse = characterService.getComicsByCharacterId(any(Long.class));

        Assert.assertNull(comicsCharacterResponse);

        mockMvc.perform(get("/v1/public/characters/15481/comics"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.status").value("We couldnt find that character"));
    }

    @Test
    public void it_should_bring_events_by_character() throws Exception {

        List<EventEntity> eventEntityList = new ArrayList<>();
        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(144L);
        eventEntity.setTitle("Title test");
        eventEntity.setDescription("Description Test");

        eventEntityList.add(eventEntity);

        eventEntity = new EventEntity();
        eventEntity.setId(877L);
        eventEntity.setTitle("Title test");
        eventEntity.setDescription("Description Test");

        eventEntityList.add(eventEntity);

        when(characterService.getEventsByCharacterId(any(Long.class))).thenReturn(eventEntityList);

        when(eventRepository.findEventsByCharacter(any(Long.class))).thenReturn(eventEntityList);

        List<EventEntity> eventCharacterResponse = characterService.getEventsByCharacterId(any(Long.class));

        Assert.assertNotNull(eventCharacterResponse);

        mockMvc.perform(get("/v1/public/characters/1548/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.copyright").value("© 2021 MARVEL"))
                .andExpect(jsonPath("$.attributionText").value("Data provided by Marvel. © 2021 MARVEL"))
                .andExpect(jsonPath("$.attributionHTML").value("<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2021 MARVEL</a>"))
                .andExpect(jsonPath("$.etag").value("55342c8b21941bfea4b795ff85633d9063e1da0e"))
                .andExpect(jsonPath("$.data.result[0][1].id").value(877))
                .andExpect(jsonPath("$.data.result[0][1].title").value("Title test"))
                .andExpect(jsonPath("$.data.result[0][1].description").value("Description Test"))
                .andExpect(jsonPath("$.data.result[0][0].id").value(144))
                .andExpect(jsonPath("$.data.result[0][0].title").value("Title test"))
                .andExpect(jsonPath("$.data.result[0][0].description").value("Description Test"));
    }

    @Test
    public void it_should_not_found_events_by_character() throws Exception {

        when(characterService.getEventsByCharacterId(any(Long.class))).thenReturn(null);

        when(eventRepository.findEventsByCharacter(any(Long.class))).thenReturn(null);

        List<EventEntity> eventsCharacterResponse = characterService.getEventsByCharacterId(any(Long.class));

        Assert.assertNull(eventsCharacterResponse);

        mockMvc.perform(get("/v1/public/characters/15481/events"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.status").value("We couldnt find that character"));
    }

    @Test
    public void it_should_bring_series_by_character() throws Exception {

        List<SeriesEntity> seriesEntityList = new ArrayList<>();
        SeriesEntity seriesEntity = new SeriesEntity();
        seriesEntity.setId(144L);
        seriesEntity.setTitle("Title test");
        seriesEntity.setDescription("Description Test");

        seriesEntityList.add(seriesEntity);

        seriesEntity = new SeriesEntity();
        seriesEntity.setId(877L);
        seriesEntity.setTitle("Title test");
        seriesEntity.setDescription("Description Test");

        seriesEntityList.add(seriesEntity);

        when(characterService.getSeriesByCharacterId(any(Long.class))).thenReturn(seriesEntityList);

        when(seriesRepository.findSeriesByCharacter(any(Long.class))).thenReturn(seriesEntityList);

        List<SeriesEntity> eventCharacterResponse = characterService.getSeriesByCharacterId(any(Long.class));

        Assert.assertNotNull(eventCharacterResponse);

        mockMvc.perform(get("/v1/public/characters/1548/series"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.copyright").value("© 2021 MARVEL"))
                .andExpect(jsonPath("$.attributionText").value("Data provided by Marvel. © 2021 MARVEL"))
                .andExpect(jsonPath("$.attributionHTML").value("<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2021 MARVEL</a>"))
                .andExpect(jsonPath("$.etag").value("55342c8b21941bfea4b795ff85633d9063e1da0e"))
                .andExpect(jsonPath("$.data.result[0][1].id").value(877))
                .andExpect(jsonPath("$.data.result[0][1].title").value("Title test"))
                .andExpect(jsonPath("$.data.result[0][1].description").value("Description Test"))
                .andExpect(jsonPath("$.data.result[0][0].id").value(144))
                .andExpect(jsonPath("$.data.result[0][0].title").value("Title test"))
                .andExpect(jsonPath("$.data.result[0][0].description").value("Description Test"));
    }

    @Test
    public void it_should_not_found_series_by_character() throws Exception {

        when(characterService.getSeriesByCharacterId(any(Long.class))).thenReturn(null);

        when(seriesRepository.findSeriesByCharacter(any(Long.class))).thenReturn(null);

        List<SeriesEntity> seriesCharacterResponse = characterService.getSeriesByCharacterId(any(Long.class));

        Assert.assertNull(seriesCharacterResponse);

        mockMvc.perform(get("/v1/public/characters/15481/series"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.status").value("We couldnt find that character"));
    }

    @Test
    public void it_should_bring_stories_by_character() throws Exception {

        List<StoryEntity> storiesEntityList = new ArrayList<>();
        StoryEntity storiesEntity = new StoryEntity();
        storiesEntity.setId(144L);
        storiesEntity.setTitle("Title test");
        storiesEntity.setDescription("Description Test");
        storiesEntity.setType("Type Test");

        storiesEntityList.add(storiesEntity);

        storiesEntity = new StoryEntity();
        storiesEntity.setId(877L);
        storiesEntity.setTitle("Title test");
        storiesEntity.setDescription("Description Test");
        storiesEntity.setType("Type Test");

        storiesEntityList.add(storiesEntity);

        when(characterService.getStoriesByCharacterId(any(Long.class))).thenReturn(storiesEntityList);

        when(storyRepository.findStoriesByCharacter(any(Long.class))).thenReturn(storiesEntityList);

        List<StoryEntity> storyCharacterResponse = characterService.getStoriesByCharacterId(any(Long.class));

        Assert.assertNotNull(storyCharacterResponse);

        mockMvc.perform(get("/v1/public/characters/1548/stories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.copyright").value("© 2021 MARVEL"))
                .andExpect(jsonPath("$.attributionText").value("Data provided by Marvel. © 2021 MARVEL"))
                .andExpect(jsonPath("$.attributionHTML").value("<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2021 MARVEL</a>"))
                .andExpect(jsonPath("$.etag").value("55342c8b21941bfea4b795ff85633d9063e1da0e"))
                .andExpect(jsonPath("$.data.result[0][1].id").value(877))
                .andExpect(jsonPath("$.data.result[0][1].title").value("Title test"))
                .andExpect(jsonPath("$.data.result[0][1].description").value("Description Test"))
                .andExpect(jsonPath("$.data.result[0][1].type").value("Type Test"))
                .andExpect(jsonPath("$.data.result[0][0].id").value(144))
                .andExpect(jsonPath("$.data.result[0][0].title").value("Title test"))
                .andExpect(jsonPath("$.data.result[0][0].description").value("Description Test"))
                .andExpect(jsonPath("$.data.result[0][0].type").value("Type Test"));
    }

    @Test
    public void it_should_not_found_stories_by_character() throws Exception {

        when(characterService.getStoriesByCharacterId(any(Long.class))).thenReturn(null);

        when(storyRepository.findStoriesByCharacter(any(Long.class))).thenReturn(null);

        List<StoryEntity> storiesCharacterResponse = characterService.getStoriesByCharacterId(any(Long.class));

        Assert.assertNull(storiesCharacterResponse);

        mockMvc.perform(get("/v1/public/characters/15481/stories"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.status").value("We couldnt find that character"));
    }

}
