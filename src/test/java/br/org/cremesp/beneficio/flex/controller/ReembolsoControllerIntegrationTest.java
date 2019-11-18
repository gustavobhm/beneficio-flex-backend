package br.org.cremesp.beneficio.flex.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import br.com.six2six.fixturefactory.Fixture;
import br.org.cremesp.beneficio.flex.Application;
import br.org.cremesp.beneficio.flex.common.DataUtils;
import br.org.cremesp.beneficio.flex.entity.Beneficio;
import br.org.cremesp.beneficio.flex.entity.Reembolso;
import br.org.cremesp.beneficio.flex.fixture.BaseFixture;
import br.org.cremesp.beneficio.flex.fixture.BeneficioFixture;
import br.org.cremesp.beneficio.flex.fixture.ReembolsoFixture;
import br.org.cremesp.beneficio.flex.repository.BeneficioRepository;
import br.org.cremesp.beneficio.flex.repository.ReembolsoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReembolsoControllerIntegrationTest extends BaseFixture {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ReembolsoRepository reembolsoRepository;

	@Autowired
	private BeneficioRepository beneficioRepository;

	@Autowired
	private Gson gson;

	private Reembolso reembolso1;

	private Reembolso reembolso2;

	@Before
	public void init() throws ParseException {

		beneficioRepository.save(Fixture //
				.from(Beneficio.class) //
				.gimme(BeneficioFixture.VALID_BENEFICIO_1) //
		);

		reembolso1 = Fixture //
				.from(Reembolso.class) //
				.gimme(ReembolsoFixture.VALID_REEMBOLSO_1);
		reembolsoRepository.save(reembolso1);

		beneficioRepository.save(Fixture //
				.from(Beneficio.class) //
				.gimme(BeneficioFixture.VALID_BENEFICIO_2) //
		);

		reembolso2 = Fixture //
				.from(Reembolso.class) //
				.gimme(ReembolsoFixture.VALID_REEMBOLSO_2);
		reembolsoRepository.save(reembolso2);

	}

	@Test
	public void getReembolso_ValidTest() throws Exception {

		mvc.perform(get("/reembolsos") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(2))) //
				.andExpect(jsonPath("$[1].solicitante", is("Solicitante 2")));
	}

	@Test
	public void getByIdReembolso_ValidTest() throws Exception {

		mvc.perform(get("/reembolsos/2") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.id", is(2))) //
				.andExpect(jsonPath("$.solicitante", is("Solicitante 2")));
	}

	@Test
	public void getByIdReembolso_InvalidTest() throws Exception {

		mvc.perform(get("/reembolsos/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void addReembolso_ValidTest() throws Exception {

		beneficioRepository.save(Fixture //
				.from(Beneficio.class) //
				.gimme(BeneficioFixture.VALID_BENEFICIO_1) //
		);

		Reembolso reembolso = Fixture //
				.from(Reembolso.class) //
				.gimme(ReembolsoFixture.VALID);

		mvc.perform(post("/reembolsos") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reembolso.convertToDTO()))) //
				.andExpect(status().isOk());
	}

	@Test
	public void addReembolso_InvalidTest() throws Exception {

		reembolso1.setSolicitante(null);

		mvc.perform(post("/reembolsos") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reembolso1.convertToDTO()))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateReembolso_ValidTest() throws Exception {

		reembolso1.setData(DataUtils.newDateWithFormat("2020-12-12"));

		mvc.perform(put("/reembolsos") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reembolso1.convertToDTO()))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateReembolso_InvalidTest() throws Exception {

		reembolso1.setId(3);

		mvc.perform(put("/reembolsos") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reembolso1.convertToDTO()))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteReembolso_ValidTest() throws Exception {

		mvc.perform(delete("/reembolsos/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk());
	}

	@Test
	public void deleteReembolso_InvalidTest() throws Exception {

		mvc.perform(delete("/reembolsos/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

}
