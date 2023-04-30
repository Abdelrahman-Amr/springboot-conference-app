package com.demo.conference.services;

import com.demo.conference.exceptions.MySessionException;
import com.demo.conference.exceptions.SpeakerException;
import com.demo.conference.models.Session;
import com.demo.conference.models.Speaker;
import com.demo.conference.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpeakerService {

    @Autowired
    private SpeakerRepository speakerRepository;

    public List<Speaker> getAll()
    {
        return speakerRepository.findAll();
    }

    public Speaker getSpeaker(Long id)
    {
        Optional<Speaker> speaker = speakerRepository.findById(id);
        if(!speaker.isPresent()) {
            throw new SpeakerException("Speaker with id = " + id + " is not found!!");
        }
        return speaker.get();
    }

    public Speaker addSpeaker(Speaker speaker)
    {
        try {
            speaker =  speakerRepository.saveAndFlush(speaker);
        }catch (Exception ex)
        {
            throw new SpeakerException("Failed to add Speaker");
        }
        return speaker;
    }

    public Speaker updateSpeaker(Long id, Speaker speaker)
    {
        Speaker existingSpeaker = this.getSpeaker(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speakerId");
        try {
            speakerRepository.saveAndFlush(existingSpeaker);
        }catch (Exception ex)
        {
            throw new SpeakerException("Failed to update Speaker with id = "+id);
        }
        return existingSpeaker;
    }

    public Speaker deleteSpeaker(Long id)
    {
        Speaker speaker = this.getSpeaker(id);
        try{
            speakerRepository.deleteById(id);
        }catch (Exception ex)
        {
            throw new SpeakerException("Failed to delete Speaker with id = "+id);
        }
        return speaker;
    }


}
