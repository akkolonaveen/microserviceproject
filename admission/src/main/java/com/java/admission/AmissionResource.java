package com.java.admission;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/admissions")
public class AmissionResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	List<Patient> patients=Arrays.asList(
			new Patient("P1","anil","Delhi"),
			new Patient("P2","raju","hyderabad"),
			new Patient("P3","vinay","pune")
			) ;
	
	List<Disease> diseases=Arrays.asList(
			new Disease("D1","malaria","medicines"),
			new Disease("D2","jaundice","warm water"),
			new Disease("D3","cholera","scanning")
			
			);
	
	@RequestMapping("/physicians")
	public EmployeesList getPhysicians()
	{
 EmployeesList  employeesList =restTemplate.getForObject("http://hr-service/hr/employees", EmployeesList.class);
		 return employeesList;
	}
	
	@RequestMapping("/variousdiseases")
	public DiseaseList getDiseases()
	{
		DiseaseList  diseaseList =restTemplate.getForObject("http://pathology-service/pathology/diseases", DiseaseList.class);
		 return diseaseList;
	}
	
	
	@RequestMapping("/diseases")
	public List<Disease> getdiseases()
	{
   return diseases;		
	}

	@RequestMapping("/patients")
	public List<Patient> getPatients()
	{
   return patients;		
	}

	@RequestMapping("patients/{Id}")
	public Patient getPatientById(@PathVariable("Id") String Id)

	{
		Patient p=patients.stream().filter(patient->Id.equals(patient.getId()))
				.findAny()
				.orElse(null);
		 return p;
	}
}
