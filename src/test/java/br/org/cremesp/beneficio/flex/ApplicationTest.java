package br.org.cremesp.beneficio.flex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.org.cremesp.beneficio.flex.Application;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Test
	public void contextLoads() {
	}

	@Test
	public void applicationStarts() {
		Application.main(new String[] {});
	}

}
