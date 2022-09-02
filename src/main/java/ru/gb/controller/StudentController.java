package ru.gb.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.dto.StudentDto;
import ru.gb.service.StudentService;

@Controller
@AllArgsConstructor
//@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/all")
    public String getStudentList(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student-list";
    }

    @GetMapping("/{studentId}")
    public String info(Model model, @PathVariable(name = "studentId") Long id) {
        StudentDto student;
        if(id != null) {
            student = studentService.findById(id);
        } else {
            return "redirect:/student/all";
        }
        model.addAttribute("student", student);
        return "student-info";
    }

    @GetMapping
    public String showForm(Model model, @RequestParam(name = "id", required = false) Long id) {
        StudentDto student;
        if (id != null) {
            student = studentService.findById(id);
        } else {
            student = new StudentDto();
        }
        model.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping
    public String saveStudent(StudentDto studentDto) {
        studentService.save(studentDto);
        return "redirect:/student/all";
    }

    @GetMapping("/delete")
    public String deleteById(@RequestParam(name = "id") Long id) {
        studentService.deleteById(id);
        return "redirect:/student/all";
    }

}
