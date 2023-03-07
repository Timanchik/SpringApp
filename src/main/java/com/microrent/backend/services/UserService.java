package com.microrent.backend.services;

import com.microrent.backend.dto.TeacherDTO;
import com.microrent.backend.models.searchingAndFiltering.SearchRequest;
import com.microrent.backend.repositories.AccountStatusRepository;
import com.microrent.backend.util.EmailSender;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.microrent.backend.models.User;
import com.microrent.backend.repositories.UserRepository;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService{
    private final UserRepository userRepository;

    private final AccountStatusRepository accountStatusRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSender emailSender;


    @Autowired
    public UserService(UserRepository userRepository, AccountStatusRepository accountStatusRepository, PasswordEncoder passwordEncoder, EmailSender emailSender) {
        this.userRepository = userRepository;
        this.accountStatusRepository = accountStatusRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender = emailSender;
    }

    public Page<User> searchOperatingSystem(SearchRequest request){
        SearchSpecification<User> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        return userRepository.findAll(specification, pageable);
    }

    @Transactional
    public void saveUser(User user) throws MessagingException, UnsupportedEncodingException {
        String hashSalt = generateRandomSalt();
        String password = generatePassword();
        user.setHashSalt(hashSalt);
        user.setPassword(passwordEncoder.encode(password + hashSalt));
        user.setStatus(accountStatusRepository.findByTitle("Activated").get());
        userRepository.save(user);
        emailSender.sendEmailRegistrationData(user.getEmail(), password);
    }

    @Transactional
    public void deactivate(int id){
        User user = userRepository.findById(id).get();
        user.setStatus(accountStatusRepository.findByTitle("Deactivated").get());
        userRepository.save(user);
    }

    @Transactional
    public void activate(int id){
        User user = userRepository.findById(id).get();
        user.setStatus(accountStatusRepository.findByTitle("Activated").get());
        userRepository.save(user);
    }

    @Transactional
    public void updateVisitDate(String email){
        User user = userRepository.findByEmail(email).get();
        user.setVisitDate(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }

    public List<User> findTeacher(String fullName){
        return userRepository.findByFullName(fullName);
    }

    private String generateRandomSalt() {
        return RandomStringUtils.randomAlphanumeric(12);
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
