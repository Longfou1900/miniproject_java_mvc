package model01.service01;

import mapper.StudentMapper;
import model01.dao01.StudentDao;
import model01.dto01.StudentRequestDto;
import model01.dto01.StudentResponseDto;
import model01.entities01.Student01;

import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImplement01 implements StudentService {

    private final StudentDao dao;
    private final StudentMapper mapper;

    public StudentServiceImplement01(StudentDao dao, StudentMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public StudentResponseDto saveStudent(StudentRequestDto requestDto) {
        Student01 student = mapper.fromStudentRequestDto(requestDto);
        Student01 savedStudent = dao.save(student);
        return mapper.toStudentResponse(savedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        // Default to page 1, size 10 so it actually returns data
        return getAllStudents(1, 10);
    }

    @Override
    public List<StudentResponseDto> getAllStudents(int page, int size) {
        List<Student01> allStudents = dao.getAll();

        if (allStudents.isEmpty()) {
            throw new RuntimeException("The student database is currently empty.");
        }

        // Business Logic: Pagination calculations
        int currentPage = Math.max(page, 1);
        int pageSize = Math.max(size, 1); // Ensure size isn't 0 or negative
        int skip = (currentPage - 1) * pageSize;

        if (skip >= allStudents.size()) {
            throw new RuntimeException("Page " + page + " does not exist. Total students: " + allStudents.size());
        }

        return allStudents.stream()
                .skip(skip)
                .limit(pageSize)
                .map(mapper::toStudentResponse)
                .collect(Collectors.toList());
    }

    // CRUD: Update implementation
    @Override
    public StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto) {
        // 1. Convert DTO to Entity for update
        Student01 studentUpdateData = mapper.fromStudentRequestDto(requestDto);

        // 2. Call DAO to update existing record
        Student01 updatedEntity = dao.updateById(id, studentUpdateData);

        if (updatedEntity == null) {
            throw new RuntimeException("Update failed: Student with ID " + id + " not found.");
        }

        return mapper.toStudentResponse(updatedEntity);
    }

    @Override
    public boolean deleteStudentById(Long id) {
        if (!dao.deleteById(id)) {
            throw new RuntimeException("Delete failed: Student with ID " + id + " does not exist.");
        }
        return true;
    }

    @Override
    public List<StudentResponseDto> searchStudentsByName(String name) {
        List<Student01> results = dao.searchByName(name);
        if (results.isEmpty()) {
            throw new RuntimeException("No students found matching name: " + name);
        }
        return results.stream()
                .map(mapper::toStudentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public int countStudents() {
        return dao.count();
    }

}