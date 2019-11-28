package br.org.cremesp.beneficio.flex.projection;

import java.util.Date;

import br.org.cremesp.beneficio.flex.entity.Beneficio;

public interface ReembolsoView {

	String getSolicitante();

	String getSecao();

	Long getValor();

	Date getData();

	Beneficio getBeneficio();

	String getObservacao();

}
