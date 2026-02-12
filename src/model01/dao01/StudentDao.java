package model01.dao01;

import model01.entities01.Student01; // Import the correct entity
import java.util.List;

public interface StudentDao {

    Student01 save(Student01 student);

    List<Student01> getAll();

    boolean deleteById(Long id);

    // Matches the Student01 type used in your implementation
    Student01 updateById(Long id, Student01 student);

    List<Student01> searchByName(String name);

    List<Student01> getAll(int offset, int limit);

    int count();

}