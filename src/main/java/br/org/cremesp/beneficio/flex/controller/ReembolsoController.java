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

import br.org.cremesp.beneficio.flex.dto.ReembolsoDTO;
import br.org.cremesp.beneficio.flex.entity.Reembolso;
import br.org.cremesp.beneficio.flex.exception.BadRequestException;
import br.org.cremesp.beneficio.flex.service.ReembolsoService;
import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reembolsos")
@Api(tags = { "API Reembolso" })
public class ReembolsoController {

	@Autowired
	private ReembolsoService reembolsoService;

	@GetMapping
	public List<Reembolso> getAll() {
		return reembolsoService.getAll();
	}

	@GetMapping("/{id}")
	public Reembolso get(@PathVariable int id) throws BadRequestException {
		return reembolsoService.get(id);
	}

	@PostMapping
	public Reembolso add(@RequestBody ReembolsoDTO reembolsoDTO) throws BadRequestException {
		return reembolsoService.add(reembolsoDTO.convertToEntity());
	}

	@PutMapping
	public Reembolso edit(@RequestBody ReembolsoDTO reembolsoDTO) throws BadRequestException {
		return reembolsoService.edit(reembolsoDTO.convertToEntity());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) throws BadRequestException {
		reembolsoService.delete(id);
	}

}
