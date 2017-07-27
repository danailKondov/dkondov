package ru.job4j.professions;

/**
* Classe represents typycal student.
* @since 27/07/2017
* @version 1
*/
public class Student {

	/**
	* variable represents students name.
	**/
	private String name;

	/**
	* Constructor with parametrs.
	**/
	public Student(String name) {
		this.name = name;
	}

	/**
	* Getter for name.
	* @return name
	**/
	public String getName() {
		return name;
	}

	/**
	* Main activity for student.
	* @return message
	**/
	public String study(Teacher teacher) {
		return name + " study " + teacher.getSpecialization() 
		+ " in school №" + teacher.getSchoolNumber() + " with " + teacher.getName();
	}
}