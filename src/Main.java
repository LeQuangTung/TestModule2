import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public  static StudentManager studentManager = new StudentManager();
    public  static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        do {
            System.out.println("---- STUDENT MANAGEMENT PROGRAM ----");
            System.out.println("Select function by number (to continue)");
            System.out.println("1. Display student list");
            System.out.println("2. Add student");
            System.out.println("3. Update student");
            System.out.println("4. Delete student");
            System.out.println("5. Sort by average point");
            System.out.println("6. Read file");
            System.out.println("7. Write file");
            System.out.println("8. Exit");
            System.out.println("Enter your choice: ");
            try {

                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 0 || choice > 8) {
                    throw new RuntimeException();
                }
                switch (choice) {
                    case 1:
                        studentManager.display(scanner);
                        break;
                    case 2:
                        studentManager.addStudent(scanner);
                        break;
                    case 3:
                        studentManager.updateStudent(scanner);
                        break;
                    case 4:
                        studentManager.deleteStudentById(scanner);
                        break;
                    case 5:
                        menuSort(scanner);
                        break;
                    case 6:
                        studentManager.readFile(scanner);
                        break;
                    case 7:
                        studentManager.writeFile(studentManager.students);
                        break;
                    case 8:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Please re-enter your selection!");
            }
        } while (true);
    }

    public static void menuSort(Scanner scanner) {
        do {
            System.out.println("SORT STUDENT BY AVERAGE POINT");
            System.out.println("1. Sort grade point average in ascending");
            System.out.println("2. Sort grade point average in descending");
            System.out.println("0. Return");
            System.out.println("Enter your choice: ");
            try {
                int choice2 = Integer.parseInt(scanner.nextLine());
                if (choice2 == 0) {
                    break;
                }
                if (choice2 < 0 || choice2 > 6) {
                    throw new RuntimeException();
                }
                switch (choice2) {
                    case 1:
                        studentManager.sortAvgPointAscending();
                        break;
                    case 2:
                        studentManager.sortAvgPointDescending();
                        break;
                }
            } catch (Exception e) {
                System.err.println("Please re-enter your selection!");
            }
        } while (true);
    }

}