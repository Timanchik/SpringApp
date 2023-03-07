package com.microrent.backend.controllers;

import com.microrent.backend.dto.GroupDto;
import com.microrent.backend.dto.search.GroupSearchResults;
import com.microrent.backend.models.searchingAndFiltering.SearchRequest;
import com.microrent.backend.services.GroupService;
import com.microrent.backend.util.ResponseMessage;
import com.microrent.backend.util.exceptions.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> createGroup(@RequestBody GroupDto groupDto){
        try {
            groupService.create(groupDto);
        } catch (ObjectNotFoundException e){
            return ResponseEntity.ok(new ResponseMessage(false, e.getMessage()));
        }
        return ResponseEntity.ok(new ResponseMessage(true, "Группа успешно добавлена !"));
    }

    @PostMapping("/search")
    public GroupSearchResults search(@RequestBody SearchRequest request){
        return groupService.searchOperatingSystem(request);
    }
}
