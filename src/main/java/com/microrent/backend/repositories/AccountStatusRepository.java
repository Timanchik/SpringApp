package com.microrent.backend.repositories;

import com.microrent.backend.models.AccountStatus;
import com.microrent.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatus, Integer> {
    Optional<AccountStatus> findByTitle(String title);
}