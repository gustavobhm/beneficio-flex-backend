package br.org.cremesp.beneficio.flex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.org.cremesp.beneficio.flex.constantes.BeneficioFlexEnum;
import br.org.cremesp.beneficio.flex.entity.Beneficio;
import br.org.cremesp.beneficio.flex.exception.BadRequestException;
import br.org.cremesp.beneficio.flex.repository.BeneficioRepository;

@Service
public class BeneficioService {

	@Autowired
	private BeneficioRepository beneficioRepository;

	public List<Beneficio> getAll() {
		return beneficioRepository.findAllByOrderByIdAsc();
	}

	public Beneficio get(int id) throws BadRequestException {
		return beneficioRepository.findById(id)
				.orElseThrow(() -> new BadRequestException(BeneficioFlexEnum.MSG_BENEFICIO_FIND_ERRO.getTexto()));
	}

	public Beneficio add(Beneficio reserva) throws BadRequestException {
		try {
			return beneficioRepository.save(reserva);
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException(BeneficioFlexEnum.MSG_BENEFICIO_SAVE_ERRO.getTexto());
		}
	}

	public Beneficio edit(Beneficio reserva) throws BadRequestException {
		Beneficio r = beneficioRepository.findById(reserva.getId())
				.orElseThrow(() -> new BadRequestException(BeneficioFlexEnum.MSG_BENEFICIO_UPDATE_ERRO.getTexto()
						+ " -> " + BeneficioFlexEnum.MSG_BENEFICIO_FIND_ERRO.getTexto()));
		r.setDescricao(reserva.getDescricao());

		return beneficioRepository.save(r);
	}

	public void delete(int id) throws BadRequestException {
		try {
			beneficioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BadRequestException(BeneficioFlexEnum.MSG_BENEFICIO_DELETE_ERRO.getTexto() + " -> "
					+ BeneficioFlexEnum.MSG_BENEFICIO_FIND_ERRO.getTexto());
		}
	}

}
