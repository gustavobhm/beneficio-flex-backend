package br.org.cremesp.beneficio.flex.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.org.cremesp.beneficio.flex.dto.BeneficioDTO;
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
@Table( //
		name = "BENEFICIO", //
		uniqueConstraints = @UniqueConstraint(columnNames = { "DESCRICAO" }) //
)
public class Beneficio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "DESCRICAO", nullable = false)
	@NotNull
	private String descricao;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficio")
	@JsonIgnore
	private final List<Reembolso> reembolso = new ArrayList<>();

	public BeneficioDTO convertToDTO() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, BeneficioDTO.class);
	}

}
