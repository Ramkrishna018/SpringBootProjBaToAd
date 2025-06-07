package com.student_crud_api.service;

import com.student_crud_api.dto.StudentDto;
import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto dto);
    StudentDto updateStudent(Long id, StudentDto dto);
    void deleteStudent(Long id);
    StudentDto getStudentById(Long id);
    List<StudentDto> getAllStudents();
}
