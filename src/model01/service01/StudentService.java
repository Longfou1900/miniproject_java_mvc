package model01.service01;

import model01.dto01.StudentRequestDto;
import model01.dto01.StudentResponseDto;
import java.util.List;

public interface StudentService {

    StudentResponseDto saveStudent(StudentRequestDto requestDto);

    List<StudentResponseDto> getAllStudents();

    boolean deleteStudentById(Long id);

    List<StudentResponseDto> searchStudentsByName(String name);

    StudentResponseDto updateStudent(Long id, StudentRequestDto updateDto);

    List<StudentResponseDto> getAllStudents(int page, int size);

    int countStudents();

}
