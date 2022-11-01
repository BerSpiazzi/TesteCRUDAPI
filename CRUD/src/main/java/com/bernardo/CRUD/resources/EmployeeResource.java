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

import com.bernardo.CRUD.entity.Employee;
import com.bernardo.CRUD.repositories.EmployeeRepository;
import javax.mail.internet.InternetAddress;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeResource {

	@Autowired
	private EmployeeRepository repository;

	@PostMapping(path = "/save")
	public ResponseEntity<Employee> save(@RequestBody Employee funcionario) {
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

	@GetMapping(path = "/get")
	public ResponseEntity<List<Employee>> get() {
		List<Employee> funcionario = new ArrayList<>();
		funcionario = repository.findAll();
		return new ResponseEntity<>(funcionario, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Optional<Employee>> deleteById(@PathVariable Integer id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<Optional<Employee>>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Optional<Employee>>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody Employee newEmployee) {

		try {
			InternetAddress emailAddr = new InternetAddress(newEmployee.getEmail());
			emailAddr.validate();
			return repository.findById(id).map(employee -> {
				employee.setName(newEmployee.getName());
				employee.setLastName(newEmployee.getLastName());
				employee.setEmail(newEmployee.getEmail());
				employee.setPis(newEmployee.getPis());
				Employee editEmployee = repository.save(employee);
				return ResponseEntity.ok().body(editEmployee);
			}).orElse(ResponseEntity.notFound().build());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
