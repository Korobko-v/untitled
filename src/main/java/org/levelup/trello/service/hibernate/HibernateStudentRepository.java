package org.levelup.trello.service.hibernate;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.levelup.trello.model.Student;
import org.levelup.trello.model.User;
import org.levelup.trello.service.StudentRepository;

@RequiredArgsConstructor
public class HibernateStudentRepository implements StudentRepository {
    private final SessionFactory factory;

    @Override
    public Student createStudent(String name, Integer rating) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            Student student = new Student(null, name, rating);
            session.persist(student);
            tx.commit();
            return student;
        }
    }
}
