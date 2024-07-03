package zoo.meal.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import zoo.meal.entity.Species;


public interface SpeciesDao extends JpaRepository<Species, Long>{}
