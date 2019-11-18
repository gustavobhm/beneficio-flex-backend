package br.org.cremesp.beneficio.flex.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.beneficio.flex.entity.Beneficio;

@Repository
public interface BeneficioRepository extends PagingAndSortingRepository<Beneficio, Integer> {
	public List<Beneficio> findAllByOrderByIdAsc();
}