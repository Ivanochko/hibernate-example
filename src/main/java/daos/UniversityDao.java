package daos;

import exceptions.UniversityAlreadyExistException;
import exceptions.UniversityNotFoundException;
import models.University;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.Optional;

public class UniversityDao {
    public Optional<Long> save(University university) {
        Transaction transaction = null;
        Long persistentUniversityId = null;

        try {
            Session session = HibernateUtil.openSession();
            transaction = session.beginTransaction();

            if (isExistById(university.getId())) {
                throw new UniversityAlreadyExistException(university.getId());
            }

            persistentUniversityId = (Long) session.save(university);

            university.setId(persistentUniversityId);

            transaction.commit();
        } catch (UniversityAlreadyExistException e) {
            transaction.rollback();
            System.out.println("UniversityDao.save() :: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.ofNullable(persistentUniversityId);
    }

    public Optional<University> getById(Long id) {
        return getById(id, true);
    }

    public Optional<University> getById(Long id, boolean isShowLog) {
        Transaction transaction;
        University persistentUniversity = null;

        try {
            Session session = HibernateUtil.openSession();
            transaction = session.beginTransaction();

            persistentUniversity = Optional.ofNullable(session.get(University.class, id))
                    .orElseThrow(() -> new UniversityNotFoundException(id));

            transaction.commit();
        } catch (UniversityNotFoundException e) {
            if (isShowLog) {
                System.out.println("UniversityDao.getById() :: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return Optional.ofNullable(persistentUniversity);
    }

    public Optional<University> update(University university) {
        Transaction transaction = null;
        University persistentUniversity = null;

        try {
            Session session = HibernateUtil.openSession();
            transaction = session.beginTransaction();

            if (!isExistById(university.getId())) {
                throw new UniversityNotFoundException(university.getId());
            }

            persistentUniversity.setName(persistentUniversity.getName());

            session.update(persistentUniversity);
            transaction.commit();
        } catch (UniversityNotFoundException e) {
            transaction.rollback();
            System.out.println("UniversityDao.update() :: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.ofNullable(persistentUniversity);
    }

    public boolean isExistById(Long id) {
        if (id == null) return false;
        return getById(id, false).isPresent();
    }
}
