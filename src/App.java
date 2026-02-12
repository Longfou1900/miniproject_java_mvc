import controller01.StudentController;
import mapper.StudentMapper;
import model01.dao01.StudentDao;
import model01.dao01.StudentDaoImplement;
import model01.service01.StudentService;
import model01.service01.StudentServiceImplement01;
import view01.Studentview01;

public class App {
    public static void main(String[] args) {
        // 1. Setup Infrastructure (Data Access & Mapping)
        StudentMapper studentMapper = new StudentMapper();
        StudentDao studentDao = new StudentDaoImplement();

        // 2. Setup Business Logic (Service Layer)
        StudentService studentService = new StudentServiceImplement01(studentDao, studentMapper);

        // 3. Setup UI (View Layer)
        // Note: Make sure the package name is correct (view.Studentview01)
        Studentview01 studentView = new Studentview01(studentService);

        // 4. Setup Orchestrator (Controller Layer)
        StudentController studentController = new StudentController(studentService, studentView);

        // 5. Start Application
        System.out.println("===============================");
        System.out.println("Student Management System v1.0");
        System.out.println("===============================");

        // The Controller now manages the displayMenu and logic flow
        studentController.run();
    }
}