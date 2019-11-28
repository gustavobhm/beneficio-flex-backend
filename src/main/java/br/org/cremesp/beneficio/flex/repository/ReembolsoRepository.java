package br.org.cremesp.beneficio.flex.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.beneficio.flex.entity.Reembolso;

@Repository
public interface ReembolsoRepository extends PagingAndSortingRepository<Reembolso, Integer> {

	public List<Reembolso> findAllByOrderByIdAsc();

}