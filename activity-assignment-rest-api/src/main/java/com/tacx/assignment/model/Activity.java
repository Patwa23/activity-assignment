package com.tacx.assignment.model;

import com.tacx.assignment.model.abstracts.AbstractTableCreation;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * It represents a record in the customer statement file.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activity")
@EqualsAndHashCode
public class Activity extends AbstractTableCreation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter @Setter(AccessLevel.PRIVATE)
    @Column(name = "activity_id")
    private UUID activityId = UUID.randomUUID();

    @Getter @Setter
    @Column(name = "activity_def")
    private String activityDef;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String type;

    @Getter @Setter
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Getter @Setter
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ACTIVITY_RECORD", joinColumns = @JoinColumn(name = "ACTIVITY_ID"),
            inverseJoinColumns = @JoinColumn(name = "RECORD_ID")
    )
    private Collection<Record> listOfRecord = new ArrayList<Record>();
}
