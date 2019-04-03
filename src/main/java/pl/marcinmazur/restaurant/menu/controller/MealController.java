package pl.marcinmazur.restaurant.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.marcinmazur.restaurant.menu.entity.Meal;
import pl.marcinmazur.restaurant.menu.service.MealService;
import pl.marcinmazur.restaurant.menu.utils.PreparationTime;

@Controller
public class MealController {

	private MealService mealService;

	@Autowired
	public MealController(MealService mealService) {
		this.mealService = mealService;
	}

	@GetMapping("/")
	public String showMeals(@RequestParam(required = false) String name,
			@RequestParam(required = false) String preparationTime, Model model) {

		List<Meal> meals = mealService.getMeals(name, preparationTime);
		model.addAttribute("meals", meals);
		model.addAttribute("preparationTimes", PreparationTime.values());

		return "meals";
	}

	@GetMapping("/meal-details/{id}")
	public String showMealDetails(@PathVariable String id, Model model) {

		Meal meal = mealService.findById(Long.parseLong(id));
		if (meal != null) {
			model.addAttribute("meal", meal);
			return "meal-details";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/add-meal")
	public String showNewMealForm(Model model) {

		model.addAttribute("meal", new Meal());
		model.addAttribute("preparationTimes", PreparationTime.values());
		model.addAttribute("action", "/add-meal");
		model.addAttribute("title", "Dodawanie dania");

		return "meal-form";
	}

	@PostMapping("/add-meal")
	public String addMeal(Meal meal, Model model) {

		System.out.println(meal);

		mealService.save(meal);

		return "redirect:/";
	}

	@GetMapping("/edit-meal/{id}")
	public String showEditMealForm(@PathVariable String id, Model model) {

		Meal meal = mealService.findById(Long.parseLong(id));
		if (meal != null) {
			model.addAttribute("meal", meal);
			model.addAttribute("preparationTimes", PreparationTime.values());
			model.addAttribute("action", "/edit-meal");
			model.addAttribute("title", "Edytowanie dania");
			return "meal-form";
		} else {
			return "redirect:/";
		}

	}

	@PostMapping("/edit-meal")
	public String editForm(Meal meal) {

		mealService.updateMeal(meal);

		return "redirect:/meal-details/" + meal.getId();
	}
}
