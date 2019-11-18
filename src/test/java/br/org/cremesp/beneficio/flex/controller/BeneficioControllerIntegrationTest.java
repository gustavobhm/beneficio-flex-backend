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
import br.org.cremesp.beneficio.flex.entity.Beneficio;
import br.org.cremesp.beneficio.flex.fixture.BaseFixture;
import br.org.cremesp.beneficio.flex.fixture.BeneficioFixture;
import br.org.cremesp.beneficio.flex.repository.BeneficioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BeneficioControllerIntegrationTest extends BaseFixture {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private BeneficioRepository beneficioRepository;

	@Autowired
	private Gson gson;

	private Beneficio beneficio1;

	private Beneficio beneficio2;

	@Before
	public void init() throws ParseException {

		beneficio1 = Fixture //
				.from(Beneficio.class) //
				.gimme(BeneficioFixture.VALID_BENEFICIO_1);
		beneficioRepository.save(beneficio1);

		beneficio2 = Fixture //
				.from(Beneficio.class) //
				.gimme(BeneficioFixture.VALID_BENEFICIO_2);
		beneficioRepository.save(beneficio2);

	}

	@Test
	public void getBeneficio_ValidTest() throws Exception {

		mvc.perform(get("/beneficios") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(2))) //
				.andExpect(jsonPath("$[1].descricao", is("Descrição 2")));
	}

	@Test
	public void getByIdBeneficio_ValidTest() throws Exception {

		mvc.perform(get("/beneficios/2") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.id", is(2))) //
				.andExpect(jsonPath("$.descricao", is("Descrição 2")));
	}

	@Test
	public void getByIdBeneficio_InvalidTest() throws Exception {

		mvc.perform(get("/beneficios/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void addBeneficio_ValidTest() throws Exception {

		Beneficio beneficio = Fixture //
				.from(Beneficio.class) //
				.gimme(BeneficioFixture.VALID);

		mvc.perform(post("/beneficios") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(beneficio.convertToDTO()))) //
				.andExpect(status().isOk());
	}

	@Test
	public void addBeneficio_InvalidTest() throws Exception {

		beneficio1.setId(null);

		mvc.perform(post("/beneficios") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(beneficio1.convertToDTO()))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateBeneficio_ValidTest() throws Exception {

		beneficio1.setDescricao("Descrição Update");

		mvc.perform(put("/beneficios") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(beneficio1.convertToDTO()))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateBeneficio_InvalidTest() throws Exception {

		beneficio1.setId(3);

		mvc.perform(put("/beneficios") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(beneficio1.convertToDTO()))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteBeneficio_ValidTest() throws Exception {

		mvc.perform(delete("/beneficios/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk());
	}

	@Test
	public void deleteBeneficio_InvalidTest() throws Exception {

		mvc.perform(delete("/beneficios/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

}
