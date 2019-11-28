package br.org.cremesp.beneficio.flex.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.cremesp.beneficio.flex.entity.Reembolso;
import br.org.cremesp.beneficio.flex.projection.DataDrilldownChartView;
import br.org.cremesp.beneficio.flex.projection.DataPieChartView;

@Repository
public interface ReportRepository extends PagingAndSortingRepository<Reembolso, Integer> {

	@Query("SELECT r.beneficio.descricao as name, r.beneficio.descricao as drilldown, COUNT(r.beneficio) as y FROM Reembolso r WHERE r.data BETWEEN :initialDate AND :finalDate GROUP BY r.beneficio ORDER BY y DESC")
	public List<DataPieChartView> getDataForPieChartBy(@Param("initialDate") Date initialDate,
			@Param("finalDate") Date finalDate);

	@Query("SELECT r.data as name, COUNT(r.beneficio) as y FROM Reembolso r WHERE r.data BETWEEN :initialDate AND :finalDate AND r.beneficio.descricao = :descricaoBeneficio GROUP BY r.data ORDER BY name DESC")
	public List<DataDrilldownChartView> getDataForDrilldownChartBy( //
			@Param("initialDate") Date initialDate, //
			@Param("finalDate") Date finalDate, //
			@Param("descricaoBeneficio") String descricaoBeneficio);
}