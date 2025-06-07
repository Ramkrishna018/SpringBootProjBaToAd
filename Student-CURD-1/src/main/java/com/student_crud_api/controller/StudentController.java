package com.student_crud_api.controller;

import com.student_crud_api.dto.StudentDto;
import com.student_crud_api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public StudentDto create(@RequestBody StudentDto dto) {
        return studentService.createStudent(dto);
    }

    @PutMapping("/{id}")
    public StudentDto update(@PathVariable("id") Long id, @RequestBody StudentDto dto) {
        return studentService.updateStudent(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "Student Deleted Successfully!";
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }


    @GetMapping
    public List<StudentDto> getAll() {
        return studentService.getAllStudents();
    }
}
