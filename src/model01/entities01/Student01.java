package model01.entities01;

import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student01 {

    private Long id;
    private String fullName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private LocalDateTime createdAt;

    // Full constructor for name, gender, and DOB
    public Student01(String fullName, Gender gender, LocalDate dateOfBirth) {
        this.id = new Random().nextLong(999);
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = LocalDateTime.now();
    }

    // Overloaded constructor for just name and DOB (to fix the errors in your list)
    public Student01(String fullName, LocalDate dateOfBirth) {
        this(fullName, null, dateOfBirth); // Defaults gender to null
    }

    public enum Gender {
        MALE, FEMALE
    }
}