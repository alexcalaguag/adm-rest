package com.cit.adm.person;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PersonResource {

	@Autowired
	private PersonRepository PersonRepository;

	@GetMapping("/persons")
	public List<Person> retrieveAllPersons() {
		return PersonRepository.findAll();
	}

	@GetMapping("/persons/{id}")
	public Person retrievePerson(@PathVariable long id) {
		Optional<Person> person = PersonRepository.findById(id);

		if (!person.isPresent())
			throw new PersonNotFoundException("id-" + id);

		return person.get();
	}

	@DeleteMapping("/persons/{id}")
	public void deletePerson(@PathVariable long id) {
		PersonRepository.deleteById(id);
	}

	@PostMapping("/persons")
	public ResponseEntity<Object> createPerson(@RequestBody Person person) {
		Person savedPerson = PersonRepository.save(person);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedPerson.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/persons/{id}")
	public ResponseEntity<Object> updatePerson(@RequestBody Person person, @PathVariable long id) {

		Optional<Person> PersonOptional = PersonRepository.findById(id);

		if (!PersonOptional.isPresent())
			return ResponseEntity.notFound().build();

		person.setId(id);

		PersonRepository.save(person);

		return ResponseEntity.noContent().build();
	}
}
