package daos;

import exceptions.StudentAlreadyExistException;
import exceptions.StudentNotFoundException;
import models.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import javax.persistence.Query;
import java.util.Optional;

public class StudentDao {

    public Optional<Long> save(Student student) {
        Transaction transaction = null;
        Long persistentStudentId = null;
        try {
            Session session = HibernateUtil.openSession();
            transaction = session.beginTransaction();

            if (isExistById(student.getId())) {
                throw new StudentAlreadyExistException(student.getId());
            }

            persistentStudentId = (Long) session.save(student);

            transaction.commit();
        } catch (StudentAlreadyExistException e) {
            transaction.rollback();
            System.out.println("save() :: " + e.getMessage());
            e.printStackTrace();
        }

        return Optional.ofNullable(persistentStudentId);
    }

    public Optional<Student> getById(Long id) {
        Transaction transaction;
        Student persistentStudent = null;

        try {
            Session session = HibernateUtil.openSession();
            transaction = session.beginTransaction();

            persistentStudent = Optional.ofNullable(session.get(Student.class, id))
                    .orElseThrow(() -> new StudentNotFoundException(id));

            transaction.commit();
        } catch (StudentNotFoundException e) {
            System.out.println("getById() :: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.ofNullable(persistentStudent);
    }

    public Optional<Student> update(Student student) {
        Transaction transaction = null;
        Student persistentStudent = null;

        try {
            Session session = HibernateUtil.openSession();
            transaction = session.beginTransaction();

            persistentStudent = Optional.ofNullable(session.get(Student.class, student.getId()))
                    .orElseThrow(() -> new StudentNotFoundException(student.getId()));

            persistentStudent.setAge(student.getAge());
            persistentStudent.setFirstName(student.getFirstName());
            persistentStudent.setLastName(student.getLastName());
            persistentStudent.setPassword(student.getPassword());

            session.update(persistentStudent);
            transaction.commit();
        } catch (StudentNotFoundException e) {
            transaction.rollback();
            System.out.println("update() :: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.ofNullable(persistentStudent);
    }

    public void updateByQuery(Student student) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.openSession();
            transaction = session.beginTransaction();

            Optional.ofNullable(session.get(Student.class, student.getId()))
                    .orElseThrow(() -> new StudentNotFoundException(student.getId()));

            String hqlQuery = "UPDATE Student set age = :age, firstName = :firstName, lastName = :lastName WHERE id = :id";
            Query query = session.createQuery(hqlQuery);
            query.setParameter("id", student.getId());
            query.setParameter("age", student.getAge());
            query.setParameter("firstName", student.getFirstName());
            query.setParameter("lastName", student.getLastName());

            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);

            transaction.commit();
        } catch (StudentNotFoundException e) {
            transaction.rollback();
            System.out.println("updateByQuery() :: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean delete(Long id) {
        Transaction transaction = null;

        try {
            Session session = HibernateUtil.openSession();
            transaction = session.beginTransaction();

            Student persistentStudent = Optional.ofNullable(session.get(Student.class, id))
                    .orElseThrow(() -> new StudentNotFoundException(id));

            session.delete(persistentStudent);
            transaction.commit();
        } catch (StudentNotFoundException e) {
            transaction.rollback();
            System.out.println("update() :: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isExistById(Long id) {
        return getById(id).isPresent();
    }
}
