package zoo.meal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zoo.meal.entity.Diet;

public interface DietDao extends JpaRepository<Diet, Long>{}
