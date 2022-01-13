package com.spring.sm;

import com.spring.sm.student.Student;
import com.spring.sm.student.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class StudentRepositoryTests {
	@Autowired private StudentRepository repo;

	@Test
	public void testAddNew(){
		Student student = new Student();
		student.setEmail("test1@email.com");
		student.setPassword("password");
		student.setFirstName("Brooke");
		student.setLastName("Leith");

		Student savedStudent = repo.save(student);

		Assertions.assertThat(savedStudent).isNotNull();
		Assertions.assertThat(savedStudent.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAll(){
		Iterable<Student> students = repo.findAll();
		Assertions.assertThat(students).hasSizeGreaterThan(0);

		for (Student student : students){
			System.out.println(student);
		}
	}

	@Test
	public void testUpdate(){
		Integer userId = 1;
		Optional<Student> optionalStudent = repo.findById(userId);
		Student student =optionalStudent.get();
		student.setPassword("newPassword");
		repo.save(student);

		Student updatedStudent = repo.findById(userId).get();
		Assertions.assertThat(updatedStudent.getPassword()).isEqualTo("newPassword");
	}

	@Test
	public void testGet(){
		Integer userId = 1;
		Optional<Student> optionalStudent = repo.findById(userId);
		Assertions.assertThat(optionalStudent).isPresent();
		System.out.println(optionalStudent.get());
	}

	@Test
	public void testDelete(){
		Integer userId = 3;
		repo.deleteById(userId);

		Optional<Student> optionalStudent = repo.findById(userId);
		Assertions.assertThat(optionalStudent).isNotPresent();
	}
}
