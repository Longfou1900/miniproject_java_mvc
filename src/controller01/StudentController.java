package controller01;

import exception.StudentException;
import model01.dto01.StudentRequestDto;
import model01.dto01.StudentResponseDto;
import model01.service01.StudentService;
import view01.Studentview01;

import java.util.List;

public class StudentController {

    private final StudentService studentService;
    private final Studentview01 studentView;

    public StudentController(StudentService studentService, Studentview01 studentView) {
        this.studentService = studentService;
        this.studentView = studentView;
    }

    public void create() {
        try {
            StudentRequestDto requestDto = studentView.inputStudentData();
            StudentResponseDto savedStudent = studentService.saveStudent(requestDto);
            System.out.println("[*] Created student: " + savedStudent.getFullname() + " (ID: " + savedStudent.getId() + ")");
        } catch (RuntimeException e) {
            System.out.println("[!] Error creating student: " + e.getMessage());
        }
    }

    public void remove() {
        Long id = studentView.showIdInput();
        if (id == -1L) return;

        try {
            studentService.deleteStudentById(id);
            System.out.println("[-] Student with ID " + id + " removed successfully.");
        } catch (RuntimeException e) {
            System.out.println("[X] " + e.getMessage());
        }
    }

    public void showData() {
        try {
            int page = 1; // start page
            int size = 5; // default page size

            int totalStudents = studentService.countStudents();
            if (totalStudents == 0) {
                System.out.println("[!] No students found.");
                return;
            }

            // Calculate total pages
            int totalPages = (int) Math.ceil((double) totalStudents / size);

            // Show first page automatically
            List<StudentResponseDto> list = studentService.getAllStudents(page, size);
            studentView.displayAll(list);

            System.out.println("\nPage " + page + " of " + totalPages +
                    (totalPages == 1 ? " page" : " pages"));

            // Pagination loop
            while (true) {
                System.out.print("[>] Do you want to go to another page? (Y/N): ");
                String choice = studentView.getStringInput().trim().toUpperCase();

                if (choice.equals("N")) {
                    break; // exit
                }

                if (choice.equals("Y")) {
                    // 1️⃣ Ask for page number
                    System.out.print("[>] Go Page Number: ");
                    int goPage = studentView.getIntegerInput();

                    if (goPage < 1 || goPage > totalPages) {
                        System.out.println("[!] Invalid page number.");
                        continue;
                    }
                    page = goPage;

//                    // 2️⃣ Ask for page size AFTER page number
//                    System.out.print("[>] Enter Page Size: ");
//                    size = studentView.getIntegerInput();
//
//                    if (size < 1) {
//                        System.out.println("[!] Page size must be greater than 0.");
//                        continue;
//                    }
//
//                    // Recalculate total pages based on new page size
//                    totalPages = (int) Math.ceil((double) totalStudents / size);

                    // Show selected page
                    List<StudentResponseDto> newList = studentService.getAllStudents(page, size);
                    studentView.displayAll(newList);

                    System.out.println("\nPage " + page + " of " + totalPages +
                            (totalPages == 1 ? " page" : " pages"));
                } else {
                    System.out.println("[!] Please enter Y or N only.");
                }
            }

        } catch (Exception e) {
            System.out.println("[!] Error: Invalid input.");
        }
    }


    // CRUD: Update Feature
    public void update() {
        try {
            Long id = studentView.showIdInput();
            if (id == -1L) return;

            System.out.println("[*] Enter New Information for Student:");
            StudentRequestDto updateDto = studentView.inputStudentData();

            StudentResponseDto updated = studentService.updateStudent(id, updateDto);
            System.out.println("[*] Student updated: " + updated.getFullname());
        } catch (RuntimeException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    public void searchByName() {
        try {
            String name = studentView.showNameInput();
            List<StudentResponseDto> results = studentService.searchStudentsByName(name);
            studentView.displayAll(results);
        } catch (RuntimeException e) {
            System.out.println("[!] " + e.getMessage());
        }
    }

    public void run() {
        while (true) {
            int option = studentView.showMenuAndGetOption();

            switch (option) {
                case 1 -> create();
                case 2 -> showData();
                case 3 -> remove();
                case 4 -> searchByName();
                case 5 -> update(); // CRUD: Update
                case 0 -> exitProgram();
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private void exitProgram() {
        System.out.print("Exiting");
        for (int i = 0; i < 3; i++) {
            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
            System.out.print(".");
        }
        System.out.println();
        System.exit(0);
    }
}