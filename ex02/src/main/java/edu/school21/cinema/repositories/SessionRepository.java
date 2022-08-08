package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Session;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session> {
    List<Session> findAllByUserId(Long id);
}
