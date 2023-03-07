package com.microrent.backend.controllers;

import com.microrent.backend.dto.search.StyleSearchResults;
import com.microrent.backend.dto.search.UserSearchResults;
import com.microrent.backend.dto.StyleDTO;
import com.microrent.backend.models.Style;
import com.microrent.backend.models.searchingAndFiltering.SearchRequest;
import com.microrent.backend.services.StyleService;
import com.microrent.backend.util.DTOConverter;
import com.microrent.backend.util.ResponseMessage;
import com.microrent.backend.util.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/style")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class StyleController {

    private final StyleService styleService;

    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addStyle(@RequestBody StyleDTO styleDTO){
        styleService.save(styleDTO);
        return ResponseEntity.ok(new ResponseMessage(true, "Стиль успешно добавлен!"));
    }

    @PostMapping("/search")
    public StyleSearchResults search(@RequestBody SearchRequest request){
        return styleService.searchOperatingSystem(request);
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseMessage> deleteStyle(@RequestBody StyleDTO styleDTO){
        try {
            styleService.delete(styleDTO);
        } catch (ObjectNotFoundException e){
            return ResponseEntity.ok(new ResponseMessage(false, e.getMessage()));
        }
        return ResponseEntity.ok(new ResponseMessage(true, "Стиль успешно удалён!"));
    }
}
