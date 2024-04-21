import java.util.*;

class Student {
    private int id;
    private String name;
    private String gender;
    private String dateOfBirth;

    public Student(int id, String name, String gender, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
}

class Lesson {
    private int id;
    private String teacherName;
    private String time;
    private List<Student> enrolledStudents;

    public Lesson(int id, String teacherName, String time) {
        this.id = id;
        this.teacherName = teacherName;
        this.time = time;
        this.enrolledStudents = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTime() {
        return time;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Student> students = new ArrayList<>();
        List<Lesson> lessons = new ArrayList<>();

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add a student");
            System.out.println("2. Add a lesson");
            System.out.println("3. Enroll a student in a lesson");
            System.out.println("4. Display student and lesson information");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addStudent(scanner, students);
                    break;
                case 2:
                    addLesson(scanner, lessons);
                    break;
                case 3:
                    enrollStudent(scanner, students, lessons);
                    break;
                case 4:
                    displayInformation(students, lessons);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static void addStudent(Scanner scanner, List<Student> students) {
        System.out.println("Enter student ID (7 digits max):");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Enter student name:");
        String name = scanner.nextLine();
        System.out.println("Enter student gender:");
        String gender = scanner.nextLine();
        System.out.println("Enter student date of birth (YYYY-MM-DD):");
        String dateOfBirth = scanner.nextLine();

        Student student = new Student(id, name, gender, dateOfBirth);
        students.add(student);
        System.out.println("Student added successfully.");
    }

    public static void addLesson(Scanner scanner, List<Lesson> lessons) {
        System.out.println("Enter lesson ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Enter teacher name for lesson:");
        String teacherName = scanner.nextLine();
        System.out.println("Enter lesson time:");
        String time = scanner.nextLine();

        Lesson lesson = new Lesson(id, teacherName, time);
        lessons.add(lesson);
        System.out.println("Lesson added successfully.");
    }
    public static void enrollStudent(Scanner scanner, List<Student> students, List<Lesson> lessons) {
        System.out.println("Enter student ID to enroll:");
        int studentId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Enter lesson ID to enroll in:");
        int lessonId = scanner.nextInt();
        scanner.nextLine(); 

        Student student = findStudentById(studentId, students);
        Lesson lesson = findLessonById(lessonId, lessons);

        if (student != null && lesson != null) {
            lesson.enrollStudent(student);
            System.out.println("Student " + student.getName() + " enrolled in lesson " + lesson.getTeacherName() + " successfully.");
        } else {
            System.out.println("Student or lesson not found. Please check IDs and try again.");
        }
    }

    public static void displayInformation(List<Student> students, List<Lesson> lessons) {
        System.out.println("Student Information:");
        for (Student student : students) {
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Gender: " + student.getGender());
            System.out.println("Date of Birth: " + student.getDateOfBirth());
            System.out.println();
        }

        System.out.println("Lesson Information:");
        for (Lesson lesson : lessons) {
            System.out.println("ID: " + lesson.getId());
            System.out.println("Teacher: " + lesson.getTeacherName());
            System.out.println("Time: " + lesson.getTime());
            System.out.println("Enrolled Students:");
            for (Student student : lesson.getEnrolledStudents()) {
                System.out.println("- " + student.getName());
            }
            System.out.println();
        }
    }

    public static Student findStudentById(int id, List<Student> students) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public static Lesson findLessonById(int id, List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            if (lesson.getId() == id) {
                return lesson;
            }
        }
        return null;
    }
}
