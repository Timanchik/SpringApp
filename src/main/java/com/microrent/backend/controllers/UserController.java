package com.microrent.backend.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microrent.backend.dto.TeacherDTO;
import com.microrent.backend.dto.search.UserSearchResults;
import com.microrent.backend.models.User;
import com.microrent.backend.models.searchingAndFiltering.SearchRequest;
import com.microrent.backend.security.JwtUserDetails;
import com.microrent.backend.services.UserService;
import com.microrent.backend.util.DTOConverter;
import com.microrent.backend.util.ResponseMessage;
import com.microrent.backend.util.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.microrent.backend.dto.UserDTO;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
public class UserController {

    private final UserService userService;
    private final DTOConverter dtoConverter;
    private final UserValidator userValidator;

    @Autowired
    public UserController(UserService userService, DTOConverter dtoConverter, UserValidator userValidator) {
        this.userService = userService;
        this.dtoConverter = dtoConverter;
        this.userValidator = userValidator;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> userCreation(@RequestBody UserDTO userDTO, BindingResult bindingResult) throws MessagingException, UnsupportedEncodingException {
        User user = dtoConverter.convertToUser(userDTO);
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()){
            return ResponseEntity.ok(new ResponseMessage(false, "Пользователь с таким email уже существует!"));
        }
        userService.saveUser(user);
        return ResponseEntity.ok(new ResponseMessage(true, "Пользователь успешно зарегистрирован"));
    }

    @PostMapping("/search")
    public UserSearchResults search(@RequestBody SearchRequest request){
        Page<User> userPage = userService.searchOperatingSystem(request);
        return new UserSearchResults(userPage.getTotalElements(), userPage.stream().map(dtoConverter::convertToUserDto).collect(Collectors.toList()));
    }

    @GetMapping("/get-user-data")
    public UserDTO getInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        return dtoConverter.convertToUserDto(jwtUserDetails.getUser());
    }

    @PostMapping("/deactivate")
    public ResponseEntity<ResponseMessage> deactivate(@RequestBody UserDTO userDTO){
        int userId = userDTO.getId();
        userService.deactivate(userId);
        return ResponseEntity.ok(new ResponseMessage(true, String.format("Пользователь с ID: %d деактивирован!", userId)));
    }

    @PostMapping("/find-teacher")
    public List<TeacherDTO> findTeacher(@RequestBody ObjectNode teacher){
        return userService.findTeacher(teacher.get("fullName").asText()).stream().map(dtoConverter::convertToTeacherDto).collect(Collectors.toList());
    }
}

