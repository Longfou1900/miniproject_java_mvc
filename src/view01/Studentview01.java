package view01;

import model01.dto01.StudentRequestDto;
import model01.dto01.StudentResponseDto;
import model01.service01.StudentService;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Studentview01 {
    public String getStringInput() {
        return scanner.nextLine();
    }

    private final Scanner scanner = new Scanner(System.in);

    public Studentview01(StudentService studentService) {}

    public StudentRequestDto inputStudentData() {
        // 1. Validate Name (Cannot be empty)
        String name = "";

        while (true) {
            System.out.print("[+] Enter Student Name: ");
            name = scanner.nextLine().trim();

            // Check empty
            if (name.isEmpty()) {
                System.out.println("[!] Name cannot be empty!");
                continue;
            }

            // Check only letters and spaces
            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("[!] Name must contain only letters and spaces!");
                continue;
            }

            break; // If valid, exit loop
        }

        // 2. Validate Gender (Must be MALE or FEMALE)
        String gender = "";
        while (!(gender.equals("MALE") || gender.equals("FEMALE"))) {
            System.out.print("[+] Enter Gender (MALE/FEMALE): ");
            gender = scanner.nextLine().toUpperCase().trim();
            if (!(gender.equals("MALE") || gender.equals("FEMALE"))) {
                System.out.println("[!] Invalid Gender! Please type 'MALE' or 'FEMALE'.");
            }
        }

        // 3. Validate Date of Birth (Must match YYYY-MM-DD)
        LocalDate dateOfBirth = null;
        while (dateOfBirth == null) {
            System.out.print("[+] Enter Date of Birth (YYYY-MM-DD): ");
            String dobInput = scanner.nextLine();
            try {
                dateOfBirth = LocalDate.parse(dobInput);
                if (dateOfBirth.isAfter(LocalDate.now())) {
                    System.out.println("[!] Date cannot be in the future!");
                    dateOfBirth = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("[!] Invalid format! Use YYYY-MM-DD (e.g., 2003-12-25)");
            }
        }

        return new StudentRequestDto(name, gender, dateOfBirth);
    }

    public void displayAll(List<StudentResponseDto> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("[!] No records found to display.");
            return;
        }

        Table table = new Table(4, BorderStyle.CLASSIC);
        table.addCell(" ID "); table.addCell(" Name ");
        table.addCell(" Gender "); table.addCell(" DoB ");

        students.forEach(s -> {
            table.addCell(s.getId() != null ? s.getId().toString() : "N/A");
            table.addCell(s.getFullname());
            table.addCell(s.getGender() != null ? s.getGender() : "N/A");
            table.addCell(s.getDateOfBirth() != null ? s.getDateOfBirth().toString() : "N/A");
        });
        System.out.println(table.render());
    }

    public int getIntegerInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int showMenuAndGetOption() {
        System.out.println("""
                ==============================
                1. Create Student
                2. Display All (Paginated)
                3. Remove Student by ID
                4. Search By Name
                5. Update Student
                0. Exit
                ==============================""");
        System.out.print("[>] Choose an option: ");
        return getIntegerInput();
    }

    public Long showIdInput() {
        while (true) {
            System.out.print("[!] Enter student ID: ");
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[!] Error: ID must be a number.");
            }
        }
    }

    public String showNameInput() {
        String name = "";
        while (name.isBlank()) {
            System.out.print("[?] Enter name to search: ");
            name = scanner.nextLine().trim();
        }
        return name;
    }
}