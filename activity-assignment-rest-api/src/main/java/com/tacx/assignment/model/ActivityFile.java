package com.tacx.assignment.model;

import com.tacx.assignment.enums.FileStatus;
import com.tacx.assignment.model.abstracts.AbstractTableCreation;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "activity_files")
public class ActivityFile extends AbstractTableCreation {
    @Id
    @Setter(AccessLevel.PRIVATE)
    private UUID id = UUID.randomUUID();

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    @Size(min = 1, max = 255)
    private String name;

    @Column(nullable = false, columnDefinition = "VARCHAR(8)")
    @Size(min = 1, max = 8)
    private String extension;

    @Column(nullable = false)
    private Long size;

    @Enumerated(EnumType.STRING)
    private FileStatus status = FileStatus.NEW;
}