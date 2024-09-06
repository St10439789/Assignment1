/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package student.management.application;

 import org.junit.jupiter.api.*;
import org.mockito.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import student.management.application.StudentManagementApplication.Student;
/**
 *
 * @author RC_Student_lab
 */
public class StudentManagementApplicationTest {
  

    private static final ByteArrayInputStream inputStream = new ByteArrayInputStream("1\n123\nJohn Doe\n20\njohn.doe@example.com\nComputer Science\n".getBytes());
    private static final PrintStream originalOut = System.out;
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUp() {
        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));
    }

    @AfterAll
    public static void tearDown() {
        System.setOut(originalOut);
    }
    private Object StudentManagement;

    @Test
    public void testCaptureStudent() {
        StudentManagement.captureStudent();

        assertEquals(1, StudentManagement.studentList.size(), "Student list size should be 1 after adding a student.");
        Student student = StudentManagement.studentList.get(0);
        assertEquals(123, student.getStudentId(), "Student ID should be 123.");
        assertEquals("John Doe", student.getName(), "Student name should be John Doe.");
        assertEquals(20, student.getAge(), "Student age should be 20.");
        assertEquals("john.doe@example.com", student.getEmail(), "Student email should be john.doe@example.com.");
        assertEquals("Computer Science", student.getCourse(), "Student course should be Computer Science.");
    }

    @Test
    public void testSearchStudent() {
        StudentManagement.studentList.clear();
        Student student = new Student(123, "John Doe", 20, "john.doe@example.com", "Computer Science");
        StudentManagement.studentList.add(student);

        // Mocking the Scanner input
        ByteArrayInputStream newInputStream = new ByteArrayInputStream("123\n".getBytes());
        System.setIn(newInputStream);

        StudentManagement.searchStudent();

        String output = outputStream.toString();
        assertTrue(output.contains("Student found: "), "Should find the student.");
    }

    @Test
    public void testDeleteStudent() {
        StudentManagement.studentList.clear();
        Student student = new Student(123, "John Doe", 20, "john.doe@example.com", "Computer Science");
        StudentManagement.studentList.add(student);

        // Mocking the Scanner input
        ByteArrayInputStream newInputStream = new ByteArrayInputStream("123\n".getBytes());
        System.setIn(newInputStream);

        StudentManagement.deleteStudent();

        assertTrue(StudentManagement.studentList.isEmpty(), "Student list should be empty after deletion.");
    }

    @Test
    public void testPrintReport() {
        StudentManagement.studentList.clear();
        Student student = new Student(123, "John Doe", 20, "john.doe@example.com", "Computer Science");
        StudentManagement.studentList.add(student);

        StudentManagement.printReport();

        String output = outputStream.toString();
        assertTrue(output.contains("Student Report:"), "Should print the student report header.");
        assertTrue(output.contains("ID: 123, Name: John Doe, Age: 20, Email: john.doe@example.com, Course: Computer Science"), "Should print student details.");
    }

    @Test
    public void testGetValidStudentAge() {
        // Mocking valid age input
        ByteArrayInputStream newInputStream = new ByteArrayInputStream("18\n".getBytes());
        System.setIn(newInputStream);
        int age = StudentManagement.getValidStudentAge(new Scanner(System.in));

        assertEquals(18, age, "Age should be valid and equal to 18.");
    }

    @Test
    public void testIsValidInteger() {
        assertTrue(StudentManagement.isValidInteger("123"), "Should return true for valid integer.");
        assertFalse(StudentManagement.isValidInteger("abc"), "Should return false for invalid integer.");
    }
}

    }
    
}
