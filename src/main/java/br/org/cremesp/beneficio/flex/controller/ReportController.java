package br.org.cremesp.beneficio.flex.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.org.cremesp.beneficio.flex.projection.DataPieChartView;
import br.org.cremesp.beneficio.flex.service.ReportService;
import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/report")
@Api(tags = { "API Report" })
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/filtrar")
	public List<DataPieChartView> getDataForPieChartBy( //
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") Date initialDate, //
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") Date finalDate) {
		return reportService.getDataForPieChartBy(initialDate, finalDate);
	}

}
