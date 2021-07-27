package com.vinicius.santos.marvelapi.repository;

import com.vinicius.santos.marvelapi.model.entity.CharacterEntity;
import com.vinicius.santos.marvelapi.model.entity.ComicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

}
