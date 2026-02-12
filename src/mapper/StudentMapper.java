package mapper;

import model01.dto01.StudentRequestDto;
import model01.dto01.StudentResponseDto;
import model01.entities01.Student01;

public class StudentMapper {

    public Student01 fromStudentRequestDto(StudentRequestDto requestDto) {
        // Handle potential null requestDto
        if (requestDto == null) return null;

        Student01.Gender genderEnum;
        try {
            // valueOf is very strict; if gender is "MAEL" instead of "MALE", it crashes.
            // We wrap it in a try-catch to default to a safe value.
            genderEnum = Student01.Gender.valueOf(requestDto.gender().toUpperCase().trim());
        } catch (Exception e) {
            // Default to a value if the input is invalid
            genderEnum = null;
        }

        return new Student01(
                requestDto.fullName(),
                genderEnum,
                requestDto.dateOfBirth()
        );
    }

    public StudentResponseDto toStudentResponse(Student01 student) {
        if (student == null) return null;

        return StudentResponseDto.builder()
                .id(student.getId())
                .fullname(student.getFullName())
                // FIX: Check if gender is null before calling .toString()
                .gender(student.getGender() != null ? student.getGender().toString() : "N/A")
                .dateOfBirth(student.getDateOfBirth())
                .build();
    }
}