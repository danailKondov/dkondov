package ru.job4j.professions;

/**
* Classe represents typycal teacher.
* @since 27/07/2017
* @version 1
*/
public class Teacher extends Profession {

	/**
	* variable represents teachers specialisation.
	**/
	private String specialization;

	/**
	* variable represents number of school where teacher works.
	**/
	private int schoolNumber;

	/**
	* Constructor with parametrs.
	**/
	public Teacher (int age, String name, String specialization, int income, int schoolNumber) {
		super(age, name, income);
		this.specialization = specialization;
		this.schoolNumber = schoolNumber;
	}

	/**
	* Main work for teacher.
	* @return message
	**/
	public String teach(Student student) {
		return name + " teach " + specialization + " in school №" + schoolNumber
		+ " students like " + student.getName();
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
	* Getter for schoolNumber.
	* @return schoolNumber
	**/
	public int getSchoolNumber() {
		return schoolNumber;
	}

	/**
	* Setter for school number.
	* @param schoolNumber represents school number
	**/
	public void setSchoolNumber(int schoolNumber) {
		this.schoolNumber = schoolNumber;
	}
}