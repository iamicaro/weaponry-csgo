package br.com.inmetrics.weaponry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.inmetrics.weaponry.controller.repository.WeaponryRepository;

@RestController
@RequestMapping("/weaponry")
public class WeaponryController {

	@Autowired 
	private WeaponryRepository repo;
	
	@GetMapping(produces="application/json")
	public @ResponseBody List<String> listWeaponry() {
		return repo.findAllWeaponry();
	}
	
	
}
