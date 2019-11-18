package br.org.cremesp.beneficio.flex.constantes;

public enum BeneficioFlexEnum {

	MSG_REEMBOLSO_FIND_ERRO("O reembolso não foi encontrado!"), //
	MSG_REEMBOLSO_SAVE_ERRO("O reembolso não foi salvo!"), //
	MSG_REEMBOLSO_UPDATE_ERRO("O reembolso não foi atualizado!"), //
	MSG_REEMBOLSO_DELETE_ERRO("O reembolso não foi excluído!"), //

	MSG_BENEFICIO_FIND_ERRO("O benefício não foi encontrado!"), //
	MSG_BENEFICIO_SAVE_ERRO("O benefício não foi salvo!"), //
	MSG_BENEFICIO_UPDATE_ERRO("O benefício não foi atualizado!"), //
	MSG_BENEFICIO_DELETE_ERRO("O benefício não foi excluído!") //

	;

	private String texto;

	private BeneficioFlexEnum(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}
}
