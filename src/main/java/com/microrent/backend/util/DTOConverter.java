package com.microrent.backend.util;

import com.microrent.backend.dto.*;
import com.microrent.backend.models.Group;
import com.microrent.backend.models.Hall;
import com.microrent.backend.models.Style;
import com.microrent.backend.models.User;
import com.microrent.backend.repositories.HallRepository;
import com.microrent.backend.repositories.StyleRepository;
import com.microrent.backend.repositories.UserRepository;
import com.microrent.backend.repositories.UserRoleRepository;
import com.microrent.backend.util.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class DTOConverter {

    private final UserRoleRepository userRoleRepository;

    private final HallRepository hallRepository;

    private final StyleRepository styleRepository;

    private final UserRepository userRepository;

    @Autowired
    public DTOConverter(UserRoleRepository userRoleRepository, HallRepository hallRepository, StyleRepository styleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.hallRepository = hallRepository;
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
    }

    public User convertToUser(UserDTO userDTO){
        User user = new User();
        user.setId(0);
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setPatronymic(userDTO.getPatronymic());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRole(userRoleRepository.findByRoleName(userDTO.getRole()).get());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public UserDTO convertToUserDto(User user){
        return new UserDTO(
                user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getPatronymic(),
                user.getDateOfBirth(),
                user.getPhoneNumber(),
                user.getRole().getRoleName(),
                user.getEmail(),
                user.getStatus().getTitle(),
                visitDateConvertor(user.getVisitDate())
        );
    }

    public HallDto convertToHallDto(Hall hall){
        return new HallDto(
                hall.getId(),
                hall.getName(),
                hall.getAddress()
        );
    }

    public Hall convertToHall(HallDto hallDto){
        Hall hall = new Hall();
        hall.setName(hallDto.getName());
        hall.setAddress(hallDto.getAddress());
        return hall;
    }

    public Style convertToStyle(StyleDTO styleDTO){
        Style style = new Style();
        style.setName(styleDTO.getName());
        style.setDescription(styleDTO.getDescription());
        return style;
    }

    public StyleDTO convertToStyleDto(Style style){
        return new StyleDTO(
                style.getId(),
                style.getName(),
                style.getDescription()
        );
    }

    public Group ConvertToGroup(GroupDto groupDto){
        Group group = new Group();
        group.setName(groupDto.getName());
        group.setDescription(groupDto.getDescription());
        Optional<Hall> hall = hallRepository.findById(groupDto.getHall().getId());
        Optional<User> teacher = userRepository.findById(groupDto.getTeacher().getId());
        Optional<Style> style = styleRepository.findById(groupDto.getStyle().getId());
        if(hall.isPresent() && teacher.isPresent() && style.isPresent()){
            group.setHall(hall.get());
            group.setTeacher(teacher.get());
            group.setStyle(style.get());
        } else {
            throw new ObjectNotFoundException("Ошибка создания, объект не найден!");
        }
        return group;
    }

    public TeacherDTO convertToTeacherDto(User user){
        return new TeacherDTO(
                user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getPatronymic());
    }

    public GroupDto convertToGroupDto(Group group){
        return new GroupDto(
                group.getId(),
                group.getName(),
                group.getDescription(),
                convertToHallDto(group.getHall()),
                convertToStyleDto(group.getStyle()),
                convertToTeacherDto(group.getTeacher()));
    }

    private String visitDateConvertor(Date visitDate){
        if(visitDate != null){
            return visitDate.toString();
        }
        return null;
    }

}
