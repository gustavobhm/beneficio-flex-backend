package br.org.cremesp.beneficio.flex.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import br.org.cremesp.beneficio.flex.entity.Beneficio;
import br.org.cremesp.beneficio.flex.entity.Reembolso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReembolsoDTO {

	private Integer id;

	private String solicitante;

	private String secao;

	private Double valor;

	private Date data;

	private Beneficio beneficio;

	private String observacao;

	public Reembolso convertToEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, Reembolso.class);
	}

}
