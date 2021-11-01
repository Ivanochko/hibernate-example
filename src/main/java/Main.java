import daos.StudentDao;
import daos.UniversityDao;
import models.Student;
import models.University;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UniversityDao universityDao = new UniversityDao();
        StudentDao studentDao = new StudentDao();

        University firstUniversity = University.builder()
                .name("PNU")
                .students(new ArrayList<>())
                .build();

        Student firstStudent = Student.builder()
                .firstName("Vasyl")
                .lastName("Ivanochko")
                .age(19)
                .password("Secret!")
                .university(firstUniversity)
                .build();

        Student secondStudent = Student.builder()
                .firstName("Roman")
                .lastName("Testovuy")
                .age(22)
                .password("AnotherSecret!")
                .build();

        universityDao.save(firstUniversity);

        studentDao.save(firstStudent);
        studentDao.save(secondStudent);

        System.out.println(studentDao.getById(firstStudent.getId()));

        firstStudent.setFirstName("Ne Vasyl!");
        studentDao.update(firstStudent);

        System.out.println(studentDao.getById(firstStudent.getId()));

        studentDao.delete(secondStudent.getId());

    }
}
