package com.demo.conference.services;

import com.demo.conference.exceptions.MySessionException;
import com.demo.conference.models.Session;
import com.demo.conference.repositories.SessionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> getAll()
    {
        return sessionRepository.findAll();
    }

    public Session getSession(Long id)
    {
           Optional<Session> session = sessionRepository.findById(id);
            if(!session.isPresent()) {
                throw new MySessionException("Session with id = " + id + " is not found!!");
            }
            return session.get();
    }

    public Session addSession(Session session)
    {
        try {
           session =  sessionRepository.saveAndFlush(session);
        }catch (Exception ex)
        {
            throw new MySessionException("Failed to add session");
        }
        return session;
    }

    public Session updateSession(Long id, Session session)
    {
        Session existingSession = this.getSession(id);
        BeanUtils.copyProperties(session, existingSession, "sessionId");
        try {
            sessionRepository.saveAndFlush(existingSession);
        }catch (Exception ex)
        {
            throw new MySessionException("Failed to update session with id = "+id);
        }
        return existingSession;
    }

    public void deleteSession(Long id)
    {
        Session session = this.getSession(id);
        try{
            sessionRepository.deleteById(id);
        }catch (Exception ex)
        {
            throw new MySessionException("Failed to delete session with id = "+id);
        }
    }

}
