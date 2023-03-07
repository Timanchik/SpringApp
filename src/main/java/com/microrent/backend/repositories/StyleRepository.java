package com.microrent.backend.repositories;

import com.microrent.backend.models.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style, Integer>, JpaSpecificationExecutor<Style> {
    Optional<Style> findById(int id);
}