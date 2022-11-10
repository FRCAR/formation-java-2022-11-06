package com.bigcorp.companies.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bigcorp.companies.rest.bean.ChildRestBean;
import com.bigcorp.companies.service.ChildService;

@RestController
public class ChildRestService {

	@Autowired
	private ChildService childService;

	@GetMapping("/children/{childId}")
	public ChildRestBean getByChildId(@PathVariable("childId") Long childId) {
		ChildRestBean bean = this.childService.findById(childId);
		if (bean == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No child found");
		}
		return bean;
	}

	@GetMapping("/children")
	public Collection<ChildRestBean> getAll() {
		return this.childService.findAll();
	}

	@DeleteMapping("/children/{childId}")
	public void deleteByChildId(@PathVariable("childId") Long id) {
		this.childService.removeById(id);
	}

	@PostMapping("/children")
	public ChildRestBean postChild(@RequestBody ChildRestBean childRestBean) {
		return this.childService.save(childRestBean);
	}
	

}