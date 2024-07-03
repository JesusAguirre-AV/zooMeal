package zoo.meal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import zoo.meal.entity.Classification;

public interface ClassificationDao extends JpaRepository<Classification, Long>{}
