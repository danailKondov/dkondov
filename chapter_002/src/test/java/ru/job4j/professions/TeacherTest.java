package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Class for testing Teacher class.
* @since 27/07/2017
* @version 1
**/
public class TeacherTest {

	/**
	* Method for testing Teacher class
	**/
	@Test
	public void TestTeacherClass() {
		Teacher teacher = new Teacher(45, "Alexandr Petrovich", "math", 15000, 238);
		Student student = new Student("Vasya Pupkin");
		String result = teacher.teach(student);
		String expected = "Alexandr Petrovich teach math in school №238 students like Vasya Pupkin";
		assertThat(result, is(expected));
		result = student.study(teacher);
		expected = "Vasya Pupkin study math in school № 238 with Alexandr Petrovich";
	}
}