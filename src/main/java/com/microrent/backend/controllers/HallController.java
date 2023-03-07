package com.microrent.backend.controllers;

import com.microrent.backend.dto.HallDto;
import com.microrent.backend.dto.search.HallSearchResults;
import com.microrent.backend.models.Hall;
import com.microrent.backend.models.searchingAndFiltering.SearchRequest;
import com.microrent.backend.services.HallService;
import com.microrent.backend.util.ResponseMessage;
import com.microrent.backend.util.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hall")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class HallController {
    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createHall(@RequestBody HallDto hallDto){
        try {
            hallService.create(hallDto);
        } catch (Exception e){
            return ResponseEntity.ok(new ResponseMessage(false, "ОШИБКА: повторяющееся значение ключа нарушает ограничение уникальности!"));
        }
        return ResponseEntity.ok(new ResponseMessage(true, "Зал успешно добавлен!"));
    }

    @PostMapping("/search")
    public HallSearchResults getHallList(@RequestBody SearchRequest request){
        return hallService.searchOperatingSystem(request);
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseMessage> deleteStyle(@RequestBody HallDto hallDto){
        try {
            hallService.delete(hallDto);
        } catch (ObjectNotFoundException e){
            return ResponseEntity.ok(new ResponseMessage(false, e.getMessage()));
        } catch (Exception e){
            return ResponseEntity.ok(new ResponseMessage(false, String.format("На ключ ID: %d всё ещё есть ссылки в таблице групп", hallDto.getId())));
        }
        return ResponseEntity.ok(new ResponseMessage(true, "Зал успешно удалён!"));
    }
}
