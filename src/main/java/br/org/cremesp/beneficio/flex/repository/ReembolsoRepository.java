package br.org.cremesp.beneficio.flex.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.cremesp.beneficio.flex.entity.Reembolso;
import br.org.cremesp.beneficio.flex.projection.DataPieChartView;

@Repository
public interface ReembolsoRepository extends PagingAndSortingRepository<Reembolso, Integer> {
	
	public List<Reembolso> findAllByOrderByIdAsc();

	@Query("SELECT r.beneficio.descricao as name, r.beneficio.descricao as drilldown, COUNT(r.beneficio) as y FROM Reembolso r WHERE r.data BETWEEN :initialDate AND :finalDate GROUP BY r.beneficio ORDER BY y DESC")
	public List<DataPieChartView> getDataForPieChartBy(@Param("initialDate") Date initialDate,@Param("finalDate") Date finalDate);
}