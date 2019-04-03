package pl.marcinmazur.restaurant.menu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.marcinmazur.restaurant.menu.entity.Meal;
import pl.marcinmazur.restaurant.menu.repository.MealRepository;
import pl.marcinmazur.restaurant.menu.utils.PreparationTime;

@Service
public class MealService {

	private MealRepository mealRepository;

	@Autowired
	public MealService(MealRepository mealRepository) {
		this.mealRepository = mealRepository;
	}

	public List<Meal> getMeals(String name, String preparationTime) {

		List<Meal> meals;

		if (name == null && (preparationTime == null || preparationTime.contentEquals("all"))) {
			meals = mealRepository.findAll();
		} else {
			PreparationTime prepTime = null;

			if (name == null)
				name = "";

			if (preparationTime != null && !preparationTime.equals("all"))
				prepTime = PreparationTime.getPreparationTimeByName(preparationTime);

			if (prepTime == null)
				meals = mealRepository.getMealsWithGivenName(name);
			else
				meals = mealRepository.getMealsWithGivenNameAndPreparationTimeAndSortingType(name, prepTime);
		}

		return meals;
	}

	public Meal findById(long id) {

		Optional<Meal> mealOptioanl = mealRepository.findById(id);

		if (mealOptioanl.isPresent())
			return mealOptioanl.get();
		else
			return null;
	}

	public void save(Meal meal) {
		mealRepository.save(meal);
	}

	public void updateMeal(Meal meal) {
		mealRepository.updateMeal(meal.getName(), meal.getDescription(), meal.getDateOfAdded(),
				meal.getPreparationTime(), meal.getPrice(), meal.getId());

	}

}
