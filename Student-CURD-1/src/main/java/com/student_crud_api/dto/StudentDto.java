package com.student_crud_api.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Long id;          // ✅ For GET, PUT responses
    private String name;
    private String rollNo;
    private String city;
    private String course;
}
