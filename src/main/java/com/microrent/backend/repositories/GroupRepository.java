package com.microrent.backend.repositories;

import com.microrent.backend.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GroupRepository extends JpaRepository<Group, Integer>, JpaSpecificationExecutor<Group> {
}