package zoo.meal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
@Data
public class Species {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long speciesId;
	private String speciesName;
	private String speciesLength;
	private String speciesHeight;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "classifi_id")
	private Classifi classifi;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(mappedBy = "species", cascade = CascadeType.PERSIST)
	private Set<Diet> diets = new HashSet<>();
}
