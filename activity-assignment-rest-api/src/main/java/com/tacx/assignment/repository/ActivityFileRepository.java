/*
 * Copyright (c) KLM Royal Dutch Airlines. All Rights Reserved.
 * ============================================================
 */

package com.tacx.assignment.repository;

import com.tacx.assignment.model.ActivityFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActivityFileRepository extends JpaRepository<ActivityFile, UUID> {
}
