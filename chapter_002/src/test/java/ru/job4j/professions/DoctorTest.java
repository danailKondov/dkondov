package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class for testing Doctor class.
* @since 27/07/2017
* @version 1
**/
public class DoctorTest {

	/**
	* Method for testing Doctor class
	**/
	@Test
	public void TestDoctorClass() {
		Doctor doctor = new Doctor(45, "German Victorovich", 70000, "physician", 25, 5);
		Student student = new Student("Vasya Pupkin");
		String result = doctor.cure(student);
		String expected = "German Victorovich is working in hospital №5 helping people like Vasya Pupkin";
		assertThat(result, is(expected));
	}
}