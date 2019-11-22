package br.org.cremesp.beneficio.flex.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class Secao {

	private String siglaSecao;

	private String descricao;

	private String situacao;

	private String tipo;

	private String centroCusto;
}