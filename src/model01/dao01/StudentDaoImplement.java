package model01.dao01;

import model01.entities01.Student01;
import java.util.List;

import static db01.StudentDB.students;

public class StudentDaoImplement implements StudentDao {

    @Override
    public Student01 save(Student01 student) {
        // Add to the static list in StudentDB
        students.add(student);
        // Return the student so the Service can convert it to a ResponseDto
        return student;
    }

    @Override
    public List<Student01> getAll() {
        return students;
    }

    @Override
    public boolean deleteById(Long id) {
        return students.removeIf(
                student -> student.getId().equals(id)
        );
    }

    @Override
    public Student01 updateById(Long id, Student01 student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                Student01 existing = students.get(i);
                existing.setFullName(student.getFullName());
                existing.setGender(student.getGender());
                existing.setDateOfBirth(student.getDateOfBirth());
                return existing;
            }
        }
        return null;
    }

    @Override
    public List<Student01> searchByName(String name) {
        return students.stream()
                .filter(stu -> stu.getFullName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public List<Student01> getAll(int offset, int limit) {
        return students.stream()
                .skip(offset)
                .limit(limit)
                .toList();
    }
    @Override
    public int count() {
        return students.size();
    }

}