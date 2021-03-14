package org.levelup.trello.service;

import org.levelup.trello.model.Student;

public interface StudentRepository {
    Student createStudent(String name, Integer rating);
}
