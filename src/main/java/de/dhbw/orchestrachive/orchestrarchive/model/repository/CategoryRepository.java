package de.dhbw.orchestrachive.orchestrarchive.model.repository;

import de.dhbw.orchestrachive.orchestrarchive.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
