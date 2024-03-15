package com.generation.clubgames.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.clubgames.model.Game;
import java.util.List;


public interface GameRepository extends JpaRepository<Game, Long> {
	public List<Game> findByNomeContainingIgnoreCase(@Param("nome") String nome);
	public List<Game> findByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}
