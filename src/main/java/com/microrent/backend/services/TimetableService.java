package com.microrent.backend.services;

import com.microrent.backend.dto.TimetableDTO;
import com.microrent.backend.models.Timetable;
import com.microrent.backend.repositories.TimetableRepository;
import com.microrent.backend.util.DTOConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TimetableService {

    private final TimetableRepository timetableRepository;

    private final DTOConverter dtoConverter;

    public TimetableService(TimetableRepository timetableRepository, DTOConverter dtoConverter) {
        this.timetableRepository = timetableRepository;
        this.dtoConverter = dtoConverter;
    }

    public List<TimetableDTO> getTimetableList(){
        return timetableRepository.findAll().stream().map(dtoConverter::convertToTimetableDTO).collect(Collectors.toList());
    }

    @Transactional
    public void create(TimetableDTO timetableDTO){
        timetableRepository.save(dtoConverter.convertToTimetable(timetableDTO));
    }
}
