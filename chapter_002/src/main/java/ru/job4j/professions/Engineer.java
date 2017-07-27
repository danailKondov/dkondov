package ru.job4j.professions;

/**
* Classe represents typycal engineer.
* @since 27/07/2017
* @version 1
*/
public class Engineer extends Profession {

	/**
	* variable represents engineers stage.
	**/
	private int stage;

	/**
	* variable represents engineers company name.
	**/
	private String companyName;

	/**
	* Constructor with parametrs.
	**/
	public Engineer(int age, String name, int income, int stage, String companyName) {
		super(age, name, income);
		this.stage = stage;
		this.companyName = companyName;
	}

	/**
	* Getter for stage.
	* @return stage
	**/
	public int getStage() {
		return stage;
	}

	/**
	* Setter for stage.
	* @param stage represents stage
	**/
	public void setStage(int stage) {
		this.stage = stage;
	}

	/**
	* Getter for companyName.
	* @return companyName
	**/
	public String getCompanyName() {
		return companyName;
	}

	/**
	* Main work for engineer.
	* @return message
	**/
	public String work() {
		return name + " is working in " + companyName;
	}
}