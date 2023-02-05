package com.devsuperior.aula.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.aula.dto.PersonDepartmentDTO;
import com.devsuperior.aula.entities.Department;
import com.devsuperior.aula.entities.Person;
import com.devsuperior.aula.repositories.DepartmentRepository;
import com.devsuperior.aula.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;
	
	@Autowired
	private DepartmentRepository deptRepository;
	
	/*
	 * for test
	 *   POST http://localhost:8080/people
	 * body: raw json
	 * 
	 * {
     *   "name": "Nova Pessoa",
     *   "salary": 8000.0,
     *   "department": {
     *     "id": 1
     *	 }
     * }	 
	 */
	
	@Transactional
	public PersonDepartmentDTO insert(PersonDepartmentDTO dto) {
		
		Person entity = new Person();
		entity.setName(dto.getName());
		entity.setSalary(dto.getSalary());
		
		// Create departament transient
		//Department dept = new Department();
		//dept.setId(dto.getDepartament().getId());
		
		// Get departament managed in this case
		// Get reference Departament from repository
		Department dept = deptRepository.getReferenceById(dto.getDepartament().getId());
		
		entity.setDepartment(dept);
		
		entity = repository.save(entity);
		
		return new PersonDepartmentDTO(entity);
		
	}
	
}
