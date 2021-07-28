package com.vinicius.santos.marvelapi.controller.v1;

import com.vinicius.santos.marvelapi.model.entity.*;
import com.vinicius.santos.marvelapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/v1/public/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;


    @GetMapping
    public @ResponseBody
    ResponseEntity getAllCharacters() {
        try {
            List<CharacterEntity> characterList = characterService.getAllCharacters();
            if (characterList != null) {
                return ResponseEntity.status(HttpStatus.OK).body(characterList);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Gson().toJson("We couldnt find any characters"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("{id}")
    public @ResponseBody
    ResponseEntity getCharacterById(@PathVariable("id") Long id) {

        try {
            Optional<CharacterEntity> character = this.characterService.getCharacterById(id);

            if (character.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(character);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Gson().toJson("We couldnt find that character"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("{id}/comics")
    public @ResponseBody
    ResponseEntity getComicsByCharacterId(@PathVariable("id") Long id) {

        try {
            List<ComicEntity> comicList = this.characterService.getComicsByCharacterId(id);

            if (comicList != null) {
                return ResponseEntity.status(HttpStatus.OK).body(comicList);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Gson().toJson("We couldnt find that character"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Gson().toJson(e.getMessage()));
        }

    }

    @GetMapping("{id}/events")
    public @ResponseBody
    ResponseEntity getEventsByCharacterId(@PathVariable("id") Long id) {

        try {
            List<EventEntity> eventList = this.characterService.getEventsByCharacterId(id);

            if (eventList != null) {
                return ResponseEntity.status(HttpStatus.OK).body(eventList);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Gson().toJson("We couldnt find that character"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Gson().toJson(e.getMessage()));
        }

    }

    @GetMapping("{id}/series")
    public @ResponseBody
    ResponseEntity getSeriesByCharacterId(@PathVariable("id") Long id) {

        try {
            List<SeriesEntity> seriesList = this.characterService.getSeriesByCharacterId(id);

            if (seriesList != null) {
                return ResponseEntity.status(HttpStatus.OK).body(seriesList);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Gson().toJson("We couldnt find that character"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Gson().toJson(e.getMessage()));
        }

    }

    @GetMapping("{id}/stories")
    public @ResponseBody
    ResponseEntity getStoriesByCharacterId(@PathVariable("id") Long id) {

        try {
            List<StoryEntity> storyEntity = this.characterService.getStoriesByCharacterId(id);

            if (storyEntity != null) {
                return ResponseEntity.status(HttpStatus.OK).body(storyEntity);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Gson().toJson("We couldnt find that character"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Gson().toJson(e.getMessage()));
        }

    }

}
