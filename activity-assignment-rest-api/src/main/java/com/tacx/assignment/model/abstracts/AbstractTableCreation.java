package com.tacx.assignment.model.abstracts;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;

@Data
@MappedSuperclass
public class AbstractTableCreation {
    @CreatedDate
    @Setter(AccessLevel.PRIVATE)
    @Column(nullable = false, updatable = false)
    private Instant created;

    @LastModifiedDate
    @Setter(AccessLevel.PRIVATE)
    @Column(nullable = false)
    private Instant updated;

    @PrePersist
    private void prePersist() {
        created = updated = Instant.now();
    }

    @PreUpdate
    private void preUpdate() {
        updated = Instant.now();
    }
}
