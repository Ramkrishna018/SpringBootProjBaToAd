package com.student_crud_api.service.impl;

import com.student_crud_api.dto.StudentDto;
import com.student_crud_api.entity.Student;
import com.student_crud_api.exception.ResourceNotFoundException;
import com.student_crud_api.repository.StudentRepository;
import com.student_crud_api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // 🔹 Create Student
    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = mapToEntity(studentDto);
        Student savedStudent = studentRepository.save(student);
        return mapToDto(savedStudent);
    }

    // 🔹 Get All Students
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // 🔹 Get Student by ID
    @Override
    public StudentDto getStudentById(Long id) {
        Student student = getStudentOrThrow(id);
        return mapToDto(student);
    }

    // 🔹 Update Student (ID से मिलाकर update करना)
    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student existingStudent = getStudentOrThrow(id);

        // Update only fields from DTO
        existingStudent.setName(studentDto.getName());
        existingStudent.setRollNo(studentDto.getRollNo());
        existingStudent.setCity(studentDto.getCity());
        existingStudent.setCourse(studentDto.getCourse());

        Student updatedStudent = studentRepository.save(existingStudent);
        return mapToDto(updatedStudent);
    }

    // 🔹 Delete Student
    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentOrThrow(id);
        studentRepository.delete(student);
    }

    // 🔁 Helper Method: ID से student लाओ या exception फेंको
    private Student getStudentOrThrow(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Student", "ID", id));
    }

    // 🔁 DTO -> Entity (ID नहीं सेट करेंगे, सिर्फ create के लिए)
    private Student mapToEntity(StudentDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setRollNo(dto.getRollNo());
        student.setCity(dto.getCity());
        student.setCourse(dto.getCourse());
        return student;
    }

    // 🔁 Entity -> DTO
    private StudentDto mapToDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId()); // 👈 ID अब DTO में डाल रहे हैं
        dto.setName(student.getName());
        dto.setRollNo(student.getRollNo());
        dto.setCity(student.getCity());
        dto.setCourse(student.getCourse());
        return dto;
    }
}
