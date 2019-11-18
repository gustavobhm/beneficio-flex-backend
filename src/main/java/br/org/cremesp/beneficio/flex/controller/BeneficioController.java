package br.org.cremesp.beneficio.flex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.cremesp.beneficio.flex.dto.BeneficioDTO;
import br.org.cremesp.beneficio.flex.entity.Beneficio;
import br.org.cremesp.beneficio.flex.exception.BadRequestException;
import br.org.cremesp.beneficio.flex.service.BeneficioService;
import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/beneficios")
@Api(tags = { "API Beneficio" })
public class BeneficioController {

	@Autowired
	private BeneficioService beneficioService;

	@GetMapping
	public List<Beneficio> getAll() {
		return beneficioService.getAll();
	}

	@GetMapping("/{id}")
	public Beneficio get(@PathVariable int id) throws BadRequestException {
		return beneficioService.get(id);
	}

	@PostMapping
	public Beneficio add(@RequestBody BeneficioDTO beneficioDTO) throws BadRequestException {
		return beneficioService.add(beneficioDTO.convertToEntity());
	}

	@PutMapping
	public Beneficio edit(@RequestBody BeneficioDTO beneficioDTO) throws BadRequestException {
		return beneficioService.edit(beneficioDTO.convertToEntity());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) throws BadRequestException {
		beneficioService.delete(id);
	}

}
