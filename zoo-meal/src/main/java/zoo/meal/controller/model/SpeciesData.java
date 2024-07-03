package zoo.meal.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import zoo.meal.entity.Classification;
import zoo.meal.entity.Diet;
import zoo.meal.entity.Species;

@Data
@NoArgsConstructor
public class SpeciesData {
	private Long speciesId;
	private String speciesName;
	private String speciesLength;
	private String speciesHeight;
	
	private SpeciesClassification speciesClassification;
	
	private Set<SpeciesDiet> speciesDiets = new HashSet<>();
	
	public SpeciesData(Species species) {
		speciesId = species.getSpeciesId();
		speciesName = species.getSpeciesName();
		speciesLength = species.getSpeciesLength();
		speciesHeight = species.getSpeciesHeight();
		
		speciesClassification = new SpeciesClassification(species.getClassification());
		
		for(Diet diet : species.getDiets()) {
			speciesDiets.add(new SpeciesDiet(diet));
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class SpeciesClassification {
		private Long classificationId;
		private String sorder;
		private String kingdom;
		private String phylum;
		private String sClassification;
		
		private Set<String> species = new HashSet<>();
		
		public SpeciesClassification(Classification classification) {
			classificationId = classification.getClassificationId();
			sorder = classification.getSorder();
			kingdom = classification.getKingdom();
			phylum = classification.getPhylum();
			sClassification = classification.getSClassification();
			
			for(Species species : classification.getSpecies()) {
				this.species.add(species.getSpeciesName());
			}
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class SpeciesDiet {
		private Long dietId;
		private String item;
		private String amount;
		private String mealTime;
		
		public SpeciesDiet(Diet diet) {
			dietId = diet.getDietId();
			item = diet.getItem();
			amount = diet.getAmount();
			mealTime = diet.getMealTime();
		}
	}
}
