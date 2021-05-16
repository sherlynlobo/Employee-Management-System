package com.sl.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sl.springboot.model.Employee;
import com.sl.springboot.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		//Before Pagination
		//model.addAttribute("listEmployees", employeeService.getAllEmployees());
		//return "index";
		
		//For pagination
		return findPaginated(1, model);
		}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		//create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
		
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute ("employee") Employee employee) {
		//save employee to the database
		employeeService.saveEmployee(employee);
		return "redirect:/";
		
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value= "id" ) long id, Model model) {
		
		//get employee from the service
		Employee employee = employeeService.getEmployeeById(id);
		
		// set employee as model attribute to pre-populate the form data
		model.addAttribute("employee", employee);
		return "update_employee";
		
		
	}
	
	@GetMapping ("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		//call delete employee method
		this.employeeService.deleteEmployeeByID(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listEmployees", listEmployees);
		return "index";
		
		
	}
	

}