package br.org.cremesp.beneficio.flex.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.org.cremesp.beneficio.flex.entity.Secao;

@FeignClient(name = "secoes", url = "${cremesp.config.secao-url}")
public interface SecaoClient {

	@GetMapping("/{siglaSecao}")
	public Secao get(@PathVariable(value = "siglaSecao") String siglaSecao);

	@GetMapping
	public List<Secao> getAll(@RequestParam(required = false, defaultValue = "true", name = "ativas") boolean ativas);
}
