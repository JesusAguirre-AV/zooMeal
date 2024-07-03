package zoo.meal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import zoo.meal.controller.model.SpeciesData;
import zoo.meal.controller.model.SpeciesData.SpeciesClassification;
import zoo.meal.controller.model.SpeciesData.SpeciesDiet;
import zoo.meal.service.ZooMealService;

@RestController
@RequestMapping("/zoo_meal/classification")
@Slf4j
public class ZooMealController {
	@Autowired
	ZooMealService zooMealService;
	
	@PostMapping("/{classificationId}/species")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SpeciesData postingSpeciesData(
			@PathVariable Long classificationId,
			@RequestBody SpeciesData speciesData) {
		log.info("Creating species {}",speciesData);
		return zooMealService.saveSpecies(classificationId, speciesData);
	}
	
	@PutMapping("/{classificationId}/species/{speciesId}")
	public SpeciesData updateSpecies(
			@PathVariable Long classificationId,
			@PathVariable Long speciesId,
			@RequestBody SpeciesData speciesData
			) {
		speciesData.setSpeciesId(speciesId);
		log.info("Updating species {}", speciesData);
		return zooMealService.saveSpecies(classificationId, speciesData);
	}
	
	@GetMapping("/{classificationId}/species")
	public List<SpeciesData> listOfSpecies(){
		log.info("Retrieving all species.");
		return zooMealService.retrieveAllSpecies();
	}
	
	@GetMapping("/{classificationId}/species/{speciesId}")
	public SpeciesData speciesById(
			@PathVariable Long speciesId, @PathVariable Long classificationId) {
		log.info("Retrieve species by id {}",speciesId);
		return zooMealService.speciesById(classificationId, speciesId);
	}
	
	@DeleteMapping("/{classificationId}/species/{speciesId}")
	public Map<String,String> deletingSpeciesById(@PathVariable Long speciesId, @PathVariable Long classificationId){
		log.info("Deleting species by Id {}", speciesId);
		zooMealService.deleteSpeciesById(classificationId, speciesId);
		Map<String, String> output = new HashMap<String,String>();
		output.put("message", "Deletion successful");
		return output;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public SpeciesClassification postingClassification(
			@RequestBody SpeciesClassification sClassification) {
		log.info("Creating classification {}", sClassification);
		return zooMealService.saveClassification(sClassification);
	}
	@GetMapping
	public List<SpeciesClassification> listOfClassification() {
		log.info("Retrieving all classifications.");
		return zooMealService.retrieveAllClassifications();
	}
	@GetMapping("/{classificationId}")
	public SpeciesClassification classificationById(@PathVariable Long classificationId) {
		log.info("Retrieve classification by id {}", classificationId);
		return zooMealService.classificationById(classificationId);
	}
	
	@PostMapping("/{classificationId}/species/{speciesId}/diet")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SpeciesDiet createDiet(
			@PathVariable Long classificationId, 
			@PathVariable Long speciesId,
			@RequestBody SpeciesDiet sDiet) {
		log.info("Creating diet {}",sDiet);
		return zooMealService.saveDiet(classificationId, speciesId, sDiet);
	}
	@GetMapping("/{classificationId}/species/{speciesId}/diet")
	public List<SpeciesDiet> listingDiets(){
		log.info("Retrieving all diets.");
		return zooMealService.retrieveAllDiets();
	}
	@PutMapping("/{classificationId}/species/{speciesId}/diet/{dietId}")
	public SpeciesDiet updateDiet (
			@PathVariable Long classificationId,
			@PathVariable Long speciesId,
			@PathVariable Long dietId,
			@RequestBody SpeciesDiet sDiet){
		sDiet.setDietId(dietId);
		log.info("Updating diet {}", sDiet);
		return zooMealService.saveDiet(classificationId, speciesId, sDiet);
	}
}
