package com.cit.adm.address;

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
public class AddressResource {

	@Autowired
	private AddressRepository addressRepository;

	@GetMapping("/address")
	public List<Address> retrieveAllAddress() {
		return addressRepository.findAll();
	}

	@GetMapping("/address/{id}")
	public Address retrieveAddress(@PathVariable long id) {
		Optional<Address> address = addressRepository.findById(id);

		if (!address.isPresent())
			throw new AddressNotFoundException("id-" + id);

		return address.get();
	}

	@DeleteMapping("/address/{id}")
	public void deleteAddress(@PathVariable long id) {
		addressRepository.deleteById(id);
	}

	@PostMapping("/address")
	public ResponseEntity<Object> createAddress(@RequestBody Address address) {
		Address savedAddress = addressRepository.save(address);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedAddress.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/address/{id}")
	public ResponseEntity<Object> updateAddress(@RequestBody Address address, @PathVariable long id) {

		Optional<Address> addressOptional = addressRepository.findById(id);

		if (!addressOptional.isPresent())
			return ResponseEntity.notFound().build();

		address.setId(id);

		addressRepository.save(address);

		return ResponseEntity.noContent().build();
	}
}
