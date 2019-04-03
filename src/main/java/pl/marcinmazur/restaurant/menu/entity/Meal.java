package pl.marcinmazur.restaurant.menu.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import pl.marcinmazur.restaurant.menu.utils.PreparationTime;

@Entity
public class Meal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfAdded;

	@Enumerated(EnumType.STRING)
	private PreparationTime preparationTime;

	private Double price;

	public Meal() {

	}

	public Meal(String name, String description, LocalDate dateOfAdded, PreparationTime preparationTime, Double price) {
		this.name = name;
		this.description = description;
		this.dateOfAdded = dateOfAdded;
		this.preparationTime = preparationTime;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateOfAdded() {
		return dateOfAdded;
	}

	public void setDateOfAdded(LocalDate dateOfAdded) {
		this.dateOfAdded = dateOfAdded;
	}

	public PreparationTime getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(PreparationTime preparationTime) {
		this.preparationTime = preparationTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
