package br.org.cremesp.beneficio.flex.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.beneficio.flex.projection.DataPieChartView;
import br.org.cremesp.beneficio.flex.repository.ReembolsoRepository;

@Service
public class ReportService {

	@Autowired
	private ReembolsoRepository reembolsoRepository;

	public List<DataPieChartView> getDataForPieChartBy(Date initialDate, Date finalDate) {
		return reembolsoRepository.getDataForPieChartBy(initialDate, finalDate);
	}

}
