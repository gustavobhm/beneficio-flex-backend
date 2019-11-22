package br.org.cremesp.beneficio.flex.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import br.org.cremesp.beneficio.flex.dto.ReembolsoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "REEMBOLSO")
public class Reembolso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "SOLICITANTE", nullable = false)
	@NotNull
	private String solicitante;

	@Column(name = "SIGLA_SECAO", nullable = false)
	@NotNull
	private String siglaSecao;

	@Column(name = "VALOR", nullable = false)
	@NotNull
	private Double valor;

	@Column(name = "DATA", nullable = false)
	@NotNull
	private Date data;

	@ManyToOne
	@JoinColumn(name = "ID_BENEFICIO", referencedColumnName = "ID", insertable = true, updatable = true, nullable = false)
	@NotNull
	private Beneficio beneficio;

	@Column(name = "OBSERVACAO")
	private String observacao;

	public ReembolsoDTO convertToDTO() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, ReembolsoDTO.class);
	}

}
