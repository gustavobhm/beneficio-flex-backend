package br.org.cremesp.beneficio.flex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.beneficio.flex.client.SecaoClient;
import br.org.cremesp.beneficio.flex.constantes.BeneficioFlexEnum;
import br.org.cremesp.beneficio.flex.entity.Reembolso;
import br.org.cremesp.beneficio.flex.entity.Secao;
import br.org.cremesp.beneficio.flex.exception.BadRequestException;
import br.org.cremesp.beneficio.flex.repository.ReembolsoRepository;

@Service
public class ReembolsoService {

	@Autowired
	private ReembolsoRepository reembolsoRepository;

	/*@Autowired
	private SecaoClient secaoClient;*/

	public List<Reembolso> getAll() {

		/*for (Secao secao : secaoClient.getAll(true)) {
			System.out.println(secao.getDescricao());
		}*/

		return reembolsoRepository.findAllByOrderByIdAsc();
	}

	public Reembolso get(int id) throws BadRequestException {
		return reembolsoRepository.findById(id)
				.orElseThrow(() -> new BadRequestException(BeneficioFlexEnum.MSG_REEMBOLSO_FIND_ERRO.getTexto()));
	}

	public Reembolso add(Reembolso reserva) throws BadRequestException {
		try {
			return reembolsoRepository.save(reserva);
		} catch (Exception e) {
			throw new BadRequestException(BeneficioFlexEnum.MSG_REEMBOLSO_SAVE_ERRO.getTexto());
		}
	}

	public Reembolso edit(Reembolso reserva) throws BadRequestException {
		Reembolso r = reembolsoRepository.findById(reserva.getId())
				.orElseThrow(() -> new BadRequestException(BeneficioFlexEnum.MSG_REEMBOLSO_UPDATE_ERRO.getTexto()
						+ " -> " + BeneficioFlexEnum.MSG_REEMBOLSO_FIND_ERRO.getTexto()));
		r.setSolicitante(reserva.getSolicitante());
		r.setSiglaSecao(reserva.getSiglaSecao());
		r.setValor(reserva.getValor());
		r.setData(reserva.getData());
		r.setBeneficio(reserva.getBeneficio());
		r.setObservacao(reserva.getObservacao());

		return reembolsoRepository.save(r);
	}

	public void delete(int id) throws BadRequestException {
		try {
			reembolsoRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(BeneficioFlexEnum.MSG_REEMBOLSO_DELETE_ERRO.getTexto() + " -> "
					+ BeneficioFlexEnum.MSG_REEMBOLSO_FIND_ERRO.getTexto());
		}
	}

}
