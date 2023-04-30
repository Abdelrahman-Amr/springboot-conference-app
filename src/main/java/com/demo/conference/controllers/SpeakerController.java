package com.demo.conference.controllers;

import com.demo.conference.models.Session;
import com.demo.conference.models.Speaker;
import com.demo.conference.repositories.SpeakerRepository;
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
    private SpeakerRepository speakerRepository;

    @GetMapping
    public ResponseEntity<List<Speaker>> getAll()
    {
        return new ResponseEntity<>(speakerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Speaker> get(@PathVariable Long id)
    {
        return new ResponseEntity<>(speakerRepository.getReferenceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Speaker> add(@RequestBody Speaker speaker)
    {
        return new ResponseEntity<>(speakerRepository.saveAndFlush(speaker), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Speaker> update(@PathVariable Long id, @RequestBody Speaker speaker)
    {
        Speaker existingSpeaker = speakerRepository.getReferenceById(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speakerId");
        return new ResponseEntity<>(speakerRepository.saveAndFlush(existingSpeaker), HttpStatus.OK);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Speaker> delete(@PathVariable Long id)
    {
        Speaker speaker = speakerRepository.getReferenceById(id);
        speakerRepository.deleteById(id);
        return new ResponseEntity<>(speaker, HttpStatus.OK);
    }
}
