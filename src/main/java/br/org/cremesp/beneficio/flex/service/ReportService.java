package br.org.cremesp.beneficio.flex.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.beneficio.flex.projection.DataDrilldownChartView;
import br.org.cremesp.beneficio.flex.projection.DataPieChartView;
import br.org.cremesp.beneficio.flex.repository.ReportRepository;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRepository;

	public List<DataPieChartView> getDataForPieChartBy(Date initialDate, Date finalDate) {
		return reportRepository.getDataForPieChartBy(initialDate, finalDate);
	}

	public List<DataDrilldownChartView> getDataForDrilldownChartBy(Date initialDate, Date finalDate,
			String descricaoBeneficio) {
		return reportRepository.getDataForDrilldownChartBy(initialDate, finalDate, descricaoBeneficio);
	}

}
