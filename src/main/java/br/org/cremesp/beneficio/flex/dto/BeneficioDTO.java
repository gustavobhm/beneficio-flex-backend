package br.org.cremesp.beneficio.flex.dto;

import org.modelmapper.ModelMapper;

import br.org.cremesp.beneficio.flex.entity.Beneficio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BeneficioDTO {

	private Integer id;

	private String descricao;

	public Beneficio convertToEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, Beneficio.class);
	}

}
