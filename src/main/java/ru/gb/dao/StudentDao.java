package ru.gb.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.entity.Student;

public interface StudentDao extends JpaRepository<Student, Long> {

}
