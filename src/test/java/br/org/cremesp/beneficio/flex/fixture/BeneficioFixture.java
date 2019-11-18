package br.org.cremesp.beneficio.flex.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.org.cremesp.beneficio.flex.entity.Beneficio;

public class BeneficioFixture implements TemplateLoader {

	public static final String VALID = "valid";
	public static final String VALID_BENEFICIO_1 = "valid_beneficio_1";
	public static final String VALID_BENEFICIO_2 = "valid_beneficio_2";

	@Override
	public void load() {
		Fixture.of(Beneficio.class).addTemplate(VALID, new Rule() {
			{
				add("id", uniqueRandom(3, 100));
				add("descricao", regex("[A-Z]{1}[A-Z a-z]{9,29}"));
			}
		});

		Fixture.of(Beneficio.class).addTemplate(VALID_BENEFICIO_1, new Rule() {
			{
				add("id", 1);
				add("descricao", "Descrição 1");
			}
		});

		Fixture.of(Beneficio.class).addTemplate(VALID_BENEFICIO_2, new Rule() {
			{
				add("id", 2);
				add("descricao", "Descrição 2");
			}
		});

	}

}
