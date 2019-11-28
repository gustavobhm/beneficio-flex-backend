package br.org.cremesp.beneficio.flex.projection;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface DataDrilldownChartView {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	Date getName();

	Long getY();

}
