package ru.job4j.professions;

/**
* Classe represents typycal profession.
* @since 27/07/2017
* @version 1
*/
public class Profession {

	/**
	* variable represents age.
	**/
	int age;

	/**
	* variable represents name.
	**/
	protected String name;

	/**
	* variable represents income.
	**/
	protected int income;

	/**
	* Default constructor.
	**/
	public Profession() {
	}

	/**
	* Constructor with parametrs.
	**/
	public Profession (int age, String name, int income) {
		this.age = age;
		this.name = name;
		this.income = income;
	}

	/**
	* Getter for age.
	* @return age
	**/
	public int getAge() {
		return age;
	}

	/**
	* Setter for age.
	* @param age represents age
	**/
	public void setAge(int age) {
		this.age = age;
	}

	/**
	* Getter for name.
	* @return name
	**/
	public String getName() {
		return name;
	}

	/**
	* Setter for name.
	* @param name represents name
	**/
	public void setName(String name) {
		this.name = name;
	}

	/**
	* Getter for income.
	* @return income
	**/
	public int getIncome() {
		return income;
	}

	/**
	* Setter for income.
	* @param income represents income
	**/
	public void setIncome(int income) {
		this.income = income;
	}

	/**
	* Method for base needs - eating.
	* @return message
	**/
	public String eat() {
		return name + " is eating";
	}

	/**
	* Method for base needs - sleeping.
	* @return message
	**/
	public String sleep(int hours) {
		return name + " is sleeping for " + hours + " hours";
	}

	/**
	* Method for going home.
	* @return message
	**/
	public String goHome() {
		return name + " is going home";
	}

	/**
	* Method for going to work.
	* @return message
	**/
	public String goToWork() {
		return name + " is going to work";
	}
}