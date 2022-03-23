package com.example.hr.adapter;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private final EmployeeEntityRepository empRepo;
	private final ModelMapper modelMapper;

	public EmployeeRepositoryJpaAdapter(EmployeeEntityRepository empRepo, ModelMapper modelMapper) {
		this.empRepo = empRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean exists(TcKimlikNo identity) {
		return empRepo.existsById(identity.getValue());
	}

	@Override
	public Employee save(Employee employee) {
		var emp = modelMapper.map(employee,EmployeeEntity.class);
		var savedEmp = empRepo.save(emp);
		return modelMapper.map(savedEmp,Employee.class);
	}

	@Override
	public Optional<Employee> findEmployeeByIdentity(TcKimlikNo identity) {
		var entity = empRepo.findById(identity.getValue());
		if (entity.isEmpty()) return Optional.empty();
		return Optional.of(modelMapper.map(entity.get(),Employee.class));
	}

	@Override
	public Optional<Employee> removeEmployee(Employee employee) {
		var identity = employee.getIdentity();
		var entity = empRepo.findById(identity .getValue());
		if (entity.isEmpty()) return Optional.empty();
		EmployeeEntity removedEntity = entity.get();
		empRepo.delete(removedEntity);
		return Optional.of(modelMapper.map(removedEntity,Employee.class));
	}

	@Override
	public List<Employee> findEmployeesByDepartment(Department department) {
		return empRepo.findAllByDepartment(department)
				      .stream()
				      .map(entity -> modelMapper.map(entity, Employee.class))
				      .toList();
	}

	@Override
	public Employee update(Employee employee) {
		var entity = modelMapper.map(employee, EmployeeEntity.class);
		return modelMapper.map(empRepo.save(entity), Employee.class);
	}

}
