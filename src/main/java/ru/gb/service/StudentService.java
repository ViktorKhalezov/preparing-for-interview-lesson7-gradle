package ru.gb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.dao.StudentDao;
import ru.gb.dto.StudentDto;
import ru.gb.entity.Student;
import ru.gb.mapper.StudentMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@AllArgsConstructor
public class StudentService {


    private final StudentDao studentDao;

    private final StudentMapper studentMapper;


    @Transactional(readOnly = true)
    public StudentDto findById(Long id) {
        return studentMapper.toStudentDto(studentDao.findById(id).orElse(null));
    }

    @Transactional(readOnly = true)
    public List<StudentDto> findAll() {
        return studentDao.findAll().stream().map(studentMapper::toStudentDto).collect(Collectors.toList());
    }

    @Transactional
    public StudentDto save(StudentDto studentDto) {
        Student student = studentMapper.toStudent(studentDto);
        if(student.getId() != null) {
            Optional<Student> studentFromDbOptional = studentDao.findById(student.getId());
            if(studentFromDbOptional.isPresent()) {
                Student studentFromDb = studentFromDbOptional.get();
                studentFromDb.setName(student.getName());
                studentFromDb.setAge(student.getAge());
                return studentMapper.toStudentDto(studentDao.save(studentFromDb));
            }
        }
        return studentMapper.toStudentDto(studentDao.save(student));
    }


    public void deleteById(Long id) {
        try {
            studentDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

    }

}
