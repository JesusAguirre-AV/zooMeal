package zoo.meal.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zoo.meal.controller.model.SpeciesData;
import zoo.meal.controller.model.SpeciesData.SpeciesClassification;
import zoo.meal.controller.model.SpeciesData.SpeciesDiet;
import zoo.meal.dao.ClassificationDao;
import zoo.meal.dao.DietDao;
import zoo.meal.dao.SpeciesDao;
import zoo.meal.entity.Classification;
import zoo.meal.entity.Diet;
import zoo.meal.entity.Species;

@Service
public class ZooMealService {
	@Autowired
	SpeciesDao speciesDao;
	@Autowired
	DietDao dietDao;
	@Autowired
	ClassificationDao classificationDao;
	
											//SPECIES
	@Transactional(readOnly = false)
	public SpeciesData saveSpecies(Long classificationId, SpeciesData speciesData) {
		Classification classification = findClassificationById(classificationId);
		Long speciesId = speciesData.getSpeciesId();
		Species species = findOrCreateSpecies(speciesId, classificationId);
		copySpeciesFields(species, speciesData);
		classification.getSpecies().add(species);
		species.setClassification(classification);
		return new SpeciesData(speciesDao.save(species));
	}
	private void copySpeciesFields(Species species, SpeciesData speciesData) {
		species.setSpeciesId(speciesData.getSpeciesId());
		species.setSpeciesName(speciesData.getSpeciesName());
		species.setSpeciesHeight(species.getSpeciesHeight());
		species.setSpeciesLength(speciesData.getSpeciesLength());
	}
	private Species findOrCreateSpecies(Long speciesId, Long classificationId) {
		Species species = new Species();
		if( !Objects.isNull(speciesId) ) {
			species = findSpeciesById(speciesId, classificationId);
		}
		return species;
	}
	private Species findSpeciesById(Long speciesId, Long classificationId) {
		Species species = speciesDao.findById(speciesId).orElseThrow(
				() -> new NoSuchElementException("Species with Id="+speciesId+" was not found."));
		if(classificationId != species.getClassification().getClassificationId()) {
			throw new IllegalArgumentException();
		}
		
		return species;
	}
	@Transactional(readOnly = true)
	public List<SpeciesData> retrieveAllSpecies() {
		List<SpeciesData> results = new LinkedList<>();
		List<Species> specieses = speciesDao.findAll();
		
		for(Species species : specieses) {
			SpeciesData sData = new SpeciesData(species);
			sData.getSpeciesDiets().clear();
			sData.setSpeciesClassification(null);
			results.add(sData);
		}
		
		return results;
	}
	@Transactional(readOnly = true)
	public SpeciesData speciesById(Long classificationId, Long speciesId) {
		return new SpeciesData(findSpeciesById(speciesId, classificationId));
	}
	public void deleteSpeciesById(Long classificationId, Long speciesId) {
		Species species = findSpeciesById(speciesId, classificationId);
		species.setClassification(null);
		speciesDao.delete(species);
	}
	
	
										//CLASSIFICATION
	@Transactional(readOnly = false)
	public SpeciesClassification saveClassification(SpeciesClassification sClassification) {
		Long classificationId = sClassification.getClassificationId();
		Classification classification = findOrCreateClassification(classificationId);
		copyClassificationField(classification, sClassification);
		return new SpeciesClassification(classificationDao.save(classification));
	}
	private void copyClassificationField(Classification classification, SpeciesClassification sClassification) {
		classification.setClassificationId(sClassification.getClassificationId());
		classification.setKingdom(sClassification.getKingdom());
		classification.setPhylum(sClassification.getPhylum());
		classification.setSorder(sClassification.getSorder());
	}
	private Classification findOrCreateClassification(Long classificationId) {
		Classification classification = new Classification();
		if( !Objects.isNull(classificationId) ) {
			classification = findClassificationById(classificationId);
		}
		return classification;
	}
	private Classification findClassificationById(Long classificationId) {
		return classificationDao.findById(classificationId).orElseThrow(
				() -> new NoSuchElementException("Classification with Id="+classificationId+" was not found."));
	}
	@Transactional(readOnly = true)
	public List<SpeciesClassification> retrieveAllClassifications() {
		List<SpeciesClassification> results = new LinkedList<>();
		List<Classification> classifications = classificationDao.findAll();
		for(Classification classification : classifications) {
			SpeciesClassification sClass = new SpeciesClassification(classification);
			sClass.getSpecies().clear();
			results.add(sClass);
		}
		return results;
	}
	@Transactional(readOnly = true)
	public SpeciesClassification classificationById(Long classificationId) {
		return new SpeciesClassification(findClassificationById(classificationId));
	}
	
										//DIET
	@Transactional(readOnly = false)
	public SpeciesDiet saveDiet(Long classificationId, Long speciesId, SpeciesDiet sDiet) {
		Species species = findSpeciesById(speciesId, classificationId);
		Diet diet = findOrCreateDiet(sDiet.getDietId(), speciesId);
		copyDietFields(diet, sDiet);
		species.getDiets().add(diet);
		diet.getSpecies().add(species);
		return new SpeciesDiet(dietDao.save(diet));
	}
	private void copyDietFields(Diet diet, SpeciesDiet sDiet) {
		diet.setDietId(sDiet.getDietId());
		diet.setAmount(sDiet.getAmount());
		diet.setItem(sDiet.getItem());
		diet.setMealTime(sDiet.getMealTime());
	}
	private Diet findOrCreateDiet(Long dietId, Long speciesId) {
		Diet diet = new Diet();
		if( !Objects.isNull(dietId) ) {
			diet = findDietById(speciesId, dietId);
		}
		return diet;
	}
	private Diet findDietById(Long speciesId, Long dietId) {
		Diet diet = dietDao.findById(dietId).orElseThrow(() ->
		new NoSuchElementException());
		for(Species species : diet.getSpecies()) {
			if(species.getSpeciesId() == speciesId) {
				return diet;
			}
		}
		throw new IllegalArgumentException();
	}
	@Transactional(readOnly = true)
	public List<SpeciesDiet> retrieveAllDiets() {
		List<SpeciesDiet> result = new LinkedList<>();
		List<Diet> diets = dietDao.findAll();
		for(Diet diet : diets) {
			result.add(new SpeciesDiet(diet));
		}
		return result;
	}
	
}
