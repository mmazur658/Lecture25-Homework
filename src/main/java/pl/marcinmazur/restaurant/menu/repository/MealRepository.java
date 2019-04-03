package pl.marcinmazur.restaurant.menu.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.marcinmazur.restaurant.menu.entity.Meal;
import pl.marcinmazur.restaurant.menu.utils.PreparationTime;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

	@Query("SELECT m FROM Meal m WHERE m.name LIKE %:name%")
	List<Meal> getMealsWithGivenName(@Param("name") String name);

	@Query("SELECT m FROM Meal m WHERE m.name LIKE %:name% AND m.preparationTime = :prepTime")
	List<Meal> getMealsWithGivenNameAndPreparationTimeAndSortingType(@Param("name") String name,
			@Param("prepTime") PreparationTime preparationTime);

	@Modifying
	@Transactional
	@Query("UPDATE Meal m SET m.name = :name, m.description = :desc, m.dateOfAdded = :date, m.preparationTime = :prepTime, m.price = :price WHERE m.id = :id")
	void updateMeal(@Param("name") String name, @Param("desc") String desc, @Param("date") LocalDate dateOfAdded,
			@Param("prepTime") PreparationTime preparationTime, @Param("price") Double price, @Param("id") Long id);

}
