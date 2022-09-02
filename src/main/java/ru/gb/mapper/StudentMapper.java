package ru.gb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.gb.dto.StudentDto;
import ru.gb.entity.Student;

@Mapper
public interface StudentMapper {

    @Mapping(source = "id", target = "id")
    Student toStudent(StudentDto studentDto);

    @Mapping(source = "id", target = "id")
    StudentDto toStudentDto(Student student);

}
