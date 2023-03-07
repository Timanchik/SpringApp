package com.microrent.backend.repositories;

import com.microrent.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(int id);
    @Query("SELECT u from User u where (u.firstName like %:fullName% or u.lastName like %:fullName% or u.patronymic like %:fullName%) and u.role.id = 4")
    List<User> findByFullName(@Param("fullName") String fullName);
}
