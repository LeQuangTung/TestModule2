import java.io.*;
import java.util.*;

public class StudentManager {
    ArrayList<Student> students = new ArrayList<>();

    public StudentManager(){
        Student student1 = new Student("CG111","St1", 18, "Nam", "Hà Nội", 9.5);
        Student student2 = new Student("CG222","St2", 19, "Nữ", "Hải Phòng", 8.0);
        Student student3 = new Student("CG333","St3", 20, "Nam", "Bắc Ninh", 7.5);
        Student student4 = new Student("CG444","St4", 21, "Nữ", "Hà Nội", 6.7);
        Student student5 = new Student("CG555","St5", 22, "Nam", "Lào Cai", 5.5);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
    }
    public void addStudent(Scanner scanner) {
        try {
            System.out.println("Enter id: ");
            String id = scanner.nextLine();
            if (checkID(id)){
                System.out.println("Id already exists!");
            } else {
                System.out.println("Enter name: ");
                String name = scanner.nextLine();
                System.out.println("Enter age: ");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter gender: ");
                String gender = scanner.nextLine();
                System.out.println("Enter address: ");
                String address = scanner.nextLine();
                System.out.println("Enter average point: ");
                Double avgPoint = Double.parseDouble(scanner.nextLine());
                Student student = new Student(id, name, age, gender, address, avgPoint);
                students.add(student);
                System.out.println("Successful added Student!");
            }

        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateStudent(Scanner scanner){
        try {
            System.out.println("Enter the id of the student you want to update:");
            String id = scanner.nextLine();
            Student studentUpdate;
            if ((studentUpdate = checkExist(id)) != null){
                System.out.println("Enter new id: ");
                String newID = scanner.nextLine();
                if (!newID.equals("")) {
                    studentUpdate.setId(newID);
                }
                System.out.println("Enter new name: ");
                String name = scanner.nextLine();
                if (!name.equals("")) {
                    studentUpdate.setName(name);
                }
                System.out.println("Enter new age: ");
                String age = scanner.nextLine();
                if (!age.equals("")) {
                    studentUpdate.setAge(Integer.parseInt(age));
                }
                System.out.println("Enter new gender: ");
                String gender = scanner.nextLine();
                if (!gender.equals("")) {
                    studentUpdate.setGender(gender);
                }
                System.out.println("Enter new address: ");
                String address = scanner.nextLine();
                if (!address.equals("")){
                    studentUpdate.setAddress(address);
                }
                System.out.println("Enter new average point: ");
                String avgPoint = scanner.nextLine();
                if (!avgPoint.equals("")) {
                    studentUpdate.setAvgPoint(Double.parseDouble(avgPoint));
                }
                System.out.println("Successful update!");
            } else {
                System.err.println("Not exist student have id " + id);
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteStudentById(Scanner scanner) {
        try {
            System.out.println("Enter the id of the Student you want to delete: ");
            String id = scanner.nextLine();
            Student studentDelete;
            if ((studentDelete = checkExist(id)) != null) {
                System.out.println("Are you sure? (Enter Y to delete)" );
                String check = scanner.nextLine();
                if (check.equals("Y")) {
                    students.remove(studentDelete);
                    System.out.println("Successful delete!");
                } else {
                    System.out.println("Unsuccessful delete!");
                }
            } else {
                System.err.println("Not exist student have id " + id);
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void displayListStudent() {
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    public void sortAvgPointDescending(){
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) Math.round(o2.getAvgPoint() - o1.getAvgPoint());
            }
        });
        displayListStudent();
    }

    public void sortAvgPointAscending(){
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return (int) Math.round(o1.getAvgPoint() - o2.getAvgPoint());
            }
        });
        displayListStudent();
    }

    public Student checkExist(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public boolean checkID(String id){
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void display(Scanner scanner) {
        if (students.size() == 0) {
            System.out.println("There are currently no students in the list");
        } else {
            for (Student student : students) {
                String confirm = scanner.nextLine();
                if (confirm.equals("")) {
                    System.out.println(student);
                }
            }
        }
    }

    static  File file = new File("src/Student.csv");

    public void readFile(Scanner scanner) {

        System.out.println("Do you want to read the file?(Y/N)");
        String confirm = scanner.nextLine();
        if (confirm.equals("Y")) {
            ArrayList<Student> arrayList = new ArrayList<>();
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String str = bufferedReader.readLine();
                while (str != null) {
                    String[] arr = str.split(",");
                    String code = arr[0];
                    String name = arr[1];
                    int age = Integer.parseInt(arr[2]);
                    String gender = arr[3];
                    String address = arr[4];
                    double GPA = Double.parseDouble((arr[5]));
                    Student student = new Student(code, name, age, gender, address, GPA);
                    arrayList.add(student);
                    str = bufferedReader.readLine();
                    students = arrayList;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    public void writeFile(ArrayList<Student> list) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Student student : students) {
                bufferedWriter.write(student.displayCSV());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
