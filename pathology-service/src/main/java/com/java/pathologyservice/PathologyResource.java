package com.java.pathologyservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/pathology")
public class PathologyResource {

	List<Disease> diseases=Arrays.asList(
			new Disease("D1","malaria","medicines"),
			new Disease("D2","jaundice","warm water"),
			new Disease("D3","cholera","scanning")
			
			);
	
	@RequestMapping("/diseases")
	public DiseaseList getEmployees()
	{
		DiseaseList diseaseList=new DiseaseList();
		diseaseList.setDiseases(diseases);
		return diseaseList;
	}
	
	
	@RequestMapping("/diseases/{Id}")
	public Disease getDiseaseById(@PathVariable("Id") String Id)
	
	{
		Disease d=diseases.stream().filter(disease->Id.equals(disease.getId()))
				.findAny()
				.orElse(null);
		return d;
	}
	
}
