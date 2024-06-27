package zoo.meal.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Classifi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classifiId;
	private String order;
	private String kingdomm;
	private String phylum;
	private String speciesClassifi;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "classifi",
			cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Species> species = new HashSet<>();
}
