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
public class Classification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classificationId;
	private String sorder;
	private String kingdom;
	private String phylum;
	private String sClassification;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "classification",
			cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Species> species = new HashSet<>();
}
