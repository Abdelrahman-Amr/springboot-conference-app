package com.demo.conference.controllers;

import com.demo.conference.models.Session;
import com.demo.conference.models.Speaker;
import com.demo.conference.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public ResponseEntity<List<Session>> getAll()
    {
        return new ResponseEntity<>(sessionRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Session> get(@PathVariable Long id)
    {
        return new ResponseEntity<>(sessionRepository.getReferenceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Session> add(@RequestBody Session session)
    {
        return new ResponseEntity<>(sessionRepository.saveAndFlush(session), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Session> update(@PathVariable Long id, @RequestBody Session session)
    {
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "sessionId");
        return new ResponseEntity<>(sessionRepository.saveAndFlush(existingSession), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Session> delete(@PathVariable Long id)
    {
        Session session = sessionRepository.getReferenceById(id);
        sessionRepository.deleteById(id);
        return new ResponseEntity<>(session, HttpStatus.OK);
    }
}
