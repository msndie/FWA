package edu.school21.cinema.services;

import edu.school21.cinema.models.Session;

import java.util.List;

public interface SessionService {
    void addSession(Session session);
    List<Session> getAllSessionsByUserId(Long id);
}
