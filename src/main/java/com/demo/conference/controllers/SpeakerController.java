package com.demo.conference.controllers;

import com.demo.conference.models.Speaker;
import com.demo.conference.services.SpeakerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

    @Autowired
    private SpeakerService speakerService;

    @GetMapping
    public ResponseEntity<List<Speaker>> getAll()
    {
        return new ResponseEntity<>(speakerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Speaker> get(@PathVariable Long id)
    {
        return new ResponseEntity<>(speakerService.getSpeaker(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Speaker> add(@RequestBody Speaker speaker)
    {
        return new ResponseEntity<>(speakerService.addSpeaker(speaker), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Speaker> update(@PathVariable Long id, @RequestBody Speaker speaker)
    {
        return new ResponseEntity<>(speakerService.updateSpeaker(id, speaker), HttpStatus.OK);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Speaker> delete(@PathVariable Long id)
    {

        return new ResponseEntity<>(speakerService.deleteSpeaker(id), HttpStatus.OK);
    }
}
