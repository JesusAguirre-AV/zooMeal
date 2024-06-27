package zoo.meal.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Diet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dietId;
	private String item;
	private String amount;
	private String mealTime;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "species_diet",
	joinColumns = @JoinColumn(name = "diet_id"),
	inverseJoinColumns = @JoinColumn(name = "species_id"))
	private Set<Species> species = new HashSet<>();
}
