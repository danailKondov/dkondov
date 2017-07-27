package ru.job4j.professions;

/**
* Classe represents typycal doctor.
* @since 27/07/2017
* @version 1
*/
public class Doctor extends Profession {

	/**
	* variable represents doctors specialisation.
	**/
	private String specialization;

	/**
	* variable represents doctors stage.
	**/
	private int stage;

	/**
	* variable represents number of hospital where doctor works.
	**/
	private int hospital;

	/**
	* Constructor with parametrs.
	**/
	public Doctor (int age, String name, int income, String specialization, int stage, int hospital) {
		super(age, name, income);
		this.specialization = specialization;
		this.stage = stage;
		this.hospital = hospital;
	}

	/**
	* Getter for specialization.
	* @return specialization
	**/
	public String getSpecialization() {
		return specialization;
	}

	/**
	* Setter for specialization.
	* @param specialization represents specialization
	**/
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
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
	* Main work for doctor.
	* @return message
	**/
	public String cure (Student student) {
		return name + " is working in hospital №" + hospital +
		" helping people like " + student.getName();
	}
}