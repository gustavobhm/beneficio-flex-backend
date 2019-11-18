package br.org.cremesp.beneficio.flex.fixture;

import org.junit.BeforeClass;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class BaseFixture {

	protected static String FIXTURES_PATH = "br.org.cremesp.beneficio.flex.fixture";

	@BeforeClass
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates(FIXTURES_PATH);
	}

}
