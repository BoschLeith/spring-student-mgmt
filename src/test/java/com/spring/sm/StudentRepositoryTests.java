package com.spring.sm;

import com.spring.sm.student.Student;
import com.spring.sm.student.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class StudentRepositoryTests {
	@Autowired private StudentRepository repo;

	@Test
	public void testAddNew(){
		Student student = new Student();
		student.setEmail("test@email.com");
		student.setPassword("password");
		student.setFirstName("Bosch");
		student.setLastName("Leith");

		Student savedStudent = repo.save(student);

		Assertions.assertThat(savedStudent).isNotNull();
		Assertions.assertThat(savedStudent.getId()).isGreaterThan(0);
	}
}
