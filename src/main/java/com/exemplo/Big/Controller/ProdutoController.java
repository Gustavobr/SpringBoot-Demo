package com.exemplo.Big.Controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.persistence.EntityNotFoundException;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.exemplo.Big.Model.Produto;
import com.exemplo.Big.repository.ProdutoRepository;

@RestController
@EnableSpringDataWebSupport
public class ProdutoController {

	@Autowired
	ProdutoRepository repository;

	@GetMapping(value = "/lista", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@CacheEvict(allEntries = true)
	@Transactional
	// @EnableCaching(mode = AdviceMode.ASPECTJ)
	public List<Produto> listar() throws EntityNotFoundException {

		try {

			List<Produto> lista = repository.findAll();
			if (!lista.isEmpty()) {
				return lista;
			}

		} catch (EntityNotFoundException ex) {

		}
		return null;
	}

	@RequestMapping(value = "/lista/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public Optional<Produto> findById(@Validated @PathVariable Long id) throws Exception, EntityNotFoundException {
		try {
			if (id != null && id != 0) {
				Optional<Produto> produto = repository.findById(id);
				return produto;
			}

		} catch (Exception ex) {
			Logger.getLogger(ex.getMessage().toUpperCase());

		}
		return null;

	}

	@DeleteMapping(value = "/lista/remover{id}")
	@Transactional
	@ResponseStatus
	public ResponseEntity<String> removeProduto(@Validated @PathVariable Long id) throws HttpStatusCodeException {
		try {
			Produto produto = repository.findById(id).get();

			repository.delete(produto);
			return ResponseEntity.ok("Objeto Removido" + produto.getNome());
		} catch (HttpStatusCodeException ex) {

		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/lista/save")
	public void saveProduto(@PathParam(value = "produto") Produto produto) throws HttpStatusCodeException, Exception {
		try {
			repository.save(produto);

		} catch (HttpStatusCodeException ex) {
			Logger.getLogger("Erro");

		}

	}
}
