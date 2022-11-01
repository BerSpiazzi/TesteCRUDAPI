package com.bernardo.CRUD.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardo.CRUD.entity.Funcionario;
import com.bernardo.CRUD.repositories.FuncionarioRepository;
import javax.mail.internet.InternetAddress;

@RestController
@RequestMapping(path = "/funcionario")
public class FuncionarioResource {

	@Autowired
	private FuncionarioRepository repository;

	@PostMapping(path="/salvar")
	public ResponseEntity<Funcionario> salvar(@RequestBody Funcionario funcionario) {
		try {
			InternetAddress emailAddr = new InternetAddress(funcionario.getEmail());
			emailAddr.validate();
			repository.save(funcionario);
			return new ResponseEntity<>(funcionario, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping(path="/buscar")
	public ResponseEntity<List<Funcionario>> buscar() {
		List<Funcionario> funcionario = new ArrayList<>();
		funcionario = repository.findAll();
		return new ResponseEntity<>(funcionario, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Optional<Funcionario>> deletarById(@PathVariable Integer id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<Optional<Funcionario>>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Optional<Funcionario>>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Funcionario> update(@PathVariable Integer id, @RequestBody Funcionario novoFuncionario) {

		try {
			InternetAddress emailAddr = new InternetAddress(novoFuncionario.getEmail());
			emailAddr.validate();
			return repository.findById(id).map(funcionario -> {
				funcionario.setNome(novoFuncionario.getNome());
				funcionario.setSobrenome(novoFuncionario.getSobrenome());
				funcionario.setEmail(novoFuncionario.getEmail());
				funcionario.setPis(novoFuncionario.getPis());
				Funcionario funcionarioEditado = repository.save(funcionario);
				return ResponseEntity.ok().body(funcionarioEditado);
			}).orElse(ResponseEntity.notFound().build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
