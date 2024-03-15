package com.generation.clubgames.model;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "tb_games")
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome do produto não pode estar vazio!")
	private String nome;

	@NotBlank(message = "A descrição não pode estar vazia!")
	private String descricao;

	@NotNull(message = "Você deve inserir o valor do produto!")
	@NumberFormat(pattern = "#,###.##")
	@Column(columnDefinition = "decimal(6,2)")
	@PositiveOrZero
	private Float valor;

	@NotNull(message = "Insira quantos produtos tem no estoque")
	@NumberFormat
	@PositiveOrZero
	private Integer quantidade;
	
	@ManyToOne
	@JsonIgnoreProperties("game")
	private Categoria categoria;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getValor() {
		return valor;
	}

	public void setValorProduto(Float valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidadeNoEstoque(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
