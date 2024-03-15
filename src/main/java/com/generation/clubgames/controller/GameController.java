package com.generation.clubgames.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.clubgames.model.Game;
import com.generation.clubgames.repository.GameRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController {
	
	@Autowired
	private GameRepository gameRepository;
	
	@GetMapping
	public ResponseEntity<List<Game>> getAll() {
		return ResponseEntity.ok(gameRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Game> getById(@PathVariable Long id) {
		return gameRepository.findById(id)
				.map(result -> ResponseEntity.ok(result))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Game>> getByName(@PathVariable String nome) {
		return ResponseEntity.ok(gameRepository.findByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Game>> getByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(gameRepository.findByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Game> post(@Valid @RequestBody Game game) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(gameRepository.save(game));
	}
	
	@PutMapping
	public ResponseEntity<Game> put(@Valid @RequestBody Game game) {
		return gameRepository.findById(game.getId())
				.map(result -> ResponseEntity.status(HttpStatus.CREATED)
						.body(gameRepository.save(game)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Game> game = gameRepository.findById(id);
		
		if(game.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		gameRepository.deleteById(id);
	}
}
