package pl.marcinmazur.restaurant.menu.utils;

public enum PreparationTime {
	VERY_SHORT("Bardzo szybko do 10 min"), SHORT("Szybko 10 - 20 min"), MEDIUM("Normalnie 20 - 40 min"),
	LONG("Długo 40 - 60 min"), VERY_LONG("Bardzo długo 60 - 90 min"),
	EXTREMELY_LONG("Ekstremalnie długo więcej jak 90 min");

	private final String desc;

	private PreparationTime(String desc) {
		this.desc = desc;
	}

	public static PreparationTime getPreparationTimeByName(String name) {

		for (PreparationTime prepTime : PreparationTime.values()) {
			if (prepTime.toString().equals(name.toUpperCase()))
				return prepTime;
		}

		return null;

	}

	public String getDesc() {
		return desc;
	}

}
