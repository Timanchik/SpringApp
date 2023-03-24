package com.microrent.backend.controllers;

import com.microrent.backend.dto.TimetableDTO;
import com.microrent.backend.models.Timetable;
import com.microrent.backend.services.TimetableService;
import com.microrent.backend.util.ResponseMessage;
import com.microrent.backend.util.exceptions.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timetable")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class TimetableController {

    private final TimetableService timetableService;

    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    @GetMapping("/get-all")
    public List<TimetableDTO> findAll(){
        return timetableService.getTimetableList();
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> create(@RequestBody TimetableDTO timetableDTO){
        try {
            timetableService.create(timetableDTO);
        } catch (Exception e){
            return ResponseEntity.ok(new ResponseMessage(false, e.getMessage()));
        }
        return ResponseEntity.ok(new ResponseMessage(true, "Занятие успешно добавлено!"));
    }
}
