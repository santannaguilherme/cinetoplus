package br.com.iteris.cinetoplus.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iteris.cinetoplus.domain.dto.request.TrilhaCreateRequest;
import br.com.iteris.cinetoplus.domain.dto.response.TrilhaResponse;
import br.com.iteris.cinetoplus.domain.entities.Trilhas;
import br.com.iteris.cinetoplus.domain.mapper.TrilhaMapper;
import br.com.iteris.cinetoplus.service.TrilhaService;



@RestController
@RequestMapping("/Trilha")
public class TrilhaController {

	private final TrilhaService trilhaService;
	private final TrilhaMapper mapper;

	@Autowired
	public TrilhaController(TrilhaService trilhaService, TrilhaMapper trilhaMapper) {
		this.trilhaService = trilhaService;
		this.mapper = trilhaMapper;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "/{id}")
	public ResponseEntity<TrilhaResponse> getById(@PathVariable Integer id) {
		return ResponseEntity.ok(mapper.toDto(trilhaService.findById(id)));
	}

    @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public ResponseEntity<List<TrilhaResponse>> list() {
		return ResponseEntity.ok(trilhaService.listTrilha().stream() //
				.map(x -> mapper.toDto(x)) //
				.collect(Collectors.toList()));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping
	public ResponseEntity<TrilhaResponse> post(@Valid @RequestBody TrilhaCreateRequest model) {

		Trilhas trilhas = trilhaService.createTrilha(mapper.fromDto(model));

		return ResponseEntity.ok(mapper.toDto(trilhas));
	}

}