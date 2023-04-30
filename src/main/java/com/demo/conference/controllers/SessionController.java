package com.demo.conference.controllers;

import com.demo.conference.models.Session;
import com.demo.conference.services.SessionService;
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
    private SessionService sessionService;

    @GetMapping
    public ResponseEntity<List<Session>> getAll()
    {
        return new ResponseEntity<>(sessionService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Session> get(@PathVariable Long id)
    {
        return new ResponseEntity<>(sessionService.getSession(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Session> add(@RequestBody Session session)
    {
        return new ResponseEntity<>(sessionService.addSession(session), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Session> update(@PathVariable Long id, @RequestBody Session session)
    {
        return new ResponseEntity<>(sessionService.updateSession(id, session), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        sessionService.deleteSession(id);
        return new ResponseEntity<>("Session has been deleted successfully", HttpStatus.OK);
    }
}
