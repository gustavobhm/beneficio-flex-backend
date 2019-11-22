package br.org.cremesp.beneficio.flex.fixture;

import java.text.SimpleDateFormat;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.org.cremesp.beneficio.flex.common.DataUtils;
import br.org.cremesp.beneficio.flex.entity.Beneficio;
import br.org.cremesp.beneficio.flex.entity.Reembolso;

public class ReembolsoFixture implements TemplateLoader {

	public static final String VALID = "valid";
	public static final String VALID_REEMBOLSO_1 = "valid_reembolso_1";
	public static final String VALID_REEMBOLSO_2 = "valid_reembolso_2";

	@Override
	public void load() {
		Fixture.of(Reembolso.class).addTemplate(VALID, new Rule() {
			{
				add("id", uniqueRandom(3, 100));
				add("solicitante", regex("[A-Z]{1}[A-Z a-z]{9,29}"));
				add("siglaSecao", regex("[A-Z]{1}[A-Z a-z]{9,29}"));
				add("valor", random(Double.class, range(1, 9999.99)));
				add("data", randomDate("2999-01-01", "2999-12-31", new SimpleDateFormat("yyyy-MM-dd")));
				add("beneficio", one(Beneficio.class, BeneficioFixture.VALID_BENEFICIO_1));
				add("observacao", regex("[A-Z]{1}[A-Z a-z]{9,29}"));
			}
		});

		Fixture.of(Reembolso.class).addTemplate(VALID_REEMBOLSO_1, new Rule() {
			{
				add("id", 1);
				add("solicitante", "Solicitante 1");
				add("siglaSecao", "Seção 1");
				add("valor", 390.99);
				add("data", DataUtils.newDateWithFormat("2019-09-12"));
				add("beneficio", one(Beneficio.class, BeneficioFixture.VALID_BENEFICIO_1));
				add("observacao", "Observação 1");
			}
		});

		Fixture.of(Reembolso.class).addTemplate(VALID_REEMBOLSO_2, new Rule() {
			{
				add("id", 2);
				add("solicitante", "Solicitante 2");
				add("siglaSecao", "Seção 2");
				add("valor", 1999.00);
				add("data", DataUtils.newDateWithFormat("2019-09-13"));
				add("beneficio", one(Beneficio.class, BeneficioFixture.VALID_BENEFICIO_2));
				add("observacao", "Observação 2");
			}
		});

	}

}
