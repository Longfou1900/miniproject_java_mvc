package db01;

import model01.entities01.Student01; // Ensure you use the correct class
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDB {

    // Changed List type to Student01 to match the objects being added
    public static List<Student01> students = new ArrayList<>() {{
        // Use the Enum Gender.MALE instead of the string "male"
        add(new Student01("Long Fou", Student01.Gender.MALE, LocalDate.of(2000, 3, 12)));
        add(new Student01("Seavleng",Student01.Gender.FEMALE, LocalDate.of(2007, 7, 7)));
        add(new Student01("Sovanreach", Student01.Gender.MALE, LocalDate.of(2010, 4, 30)));
        add(new Student01("Seanghour", Student01.Gender.MALE, LocalDate.of(2005, 12, 20)));
        add(new Student01("Lyta",Student01.Gender.FEMALE, LocalDate.of(2008, 9, 9)));
        add(new Student01("Chivan",Student01.Gender.FEMALE, LocalDate.of(2006, 6, 6)));
        add(new Student01("Sokunthea", Student01.Gender.FEMALE, LocalDate.of(2002, 5, 20)));
        add(new Student01("Vibol", Student01.Gender.MALE, LocalDate.of(2001, 11, 15)));
        add(new Student01("Chanda", Student01.Gender.FEMALE, LocalDate.of(2003, 1, 30)));
        add(new Student01("Rithy", Student01.Gender.MALE, LocalDate.of(2000, 8, 12)));
        add(new Student01("Bopha", Student01.Gender.FEMALE, LocalDate.of(2005, 12, 25)));
        add(new Student01("Phirun", Student01.Gender.MALE, LocalDate.of(2004, 4, 18)));
        add(new Student01("Srey Leak", Student01.Gender.FEMALE, LocalDate.of(2006, 9, 5)));
        add(new Student01("Nara", Student01.Gender.MALE, LocalDate.of(2002, 2, 14)));
        add(new Student01("Malis", Student01.Gender.FEMALE, LocalDate.of(2007, 10, 10)));
        add(new Student01("Dara", Student01.Gender.MALE, LocalDate.of(2003, 6, 22)));
        add(new Student01("Kanhara", Student01.Gender.FEMALE, LocalDate.of(2001, 3, 3)));
        add(new Student01("Sovann", Student01.Gender.MALE, LocalDate.of(2005, 7, 19)));
        add(new Student01("Thida", Student01.Gender.FEMALE, LocalDate.of(2004, 11, 1)));
        add(new Student01("Piseth", Student01.Gender.MALE, LocalDate.of(2000, 12, 31)));
        add(new Student01("Vatana", Student01.Gender.MALE, LocalDate.of(2006, 5, 14)));
        add(new Student01("Kunthea", Student01.Gender.FEMALE, LocalDate.of(2002, 8, 27)));
        add(new Student01("Samnang", Student01.Gender.MALE, LocalDate.of(2003, 4, 2)));
        add(new Student01("Leakhena", Student01.Gender.FEMALE, LocalDate.of(2007, 1, 15)));
        add(new Student01("Odom", Student01.Gender.MALE, LocalDate.of(2001, 9, 9)));
        add(new Student01("Sreypich", Student01.Gender.FEMALE, LocalDate.of(2004, 10, 21)));
    }};
}