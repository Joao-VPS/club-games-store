package com.generation.clubgames.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.clubgames.model.Categoria;
import java.util.List;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	public List<Categoria> findByDescricaoContainingIgnoreCase(String descricao);
}
