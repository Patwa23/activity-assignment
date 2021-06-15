package com.tacx.assignment.model;

import com.tacx.assignment.model.abstracts.AbstractTableCreation;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * It represents a record in the customer statement file.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "record")
public class Record extends AbstractTableCreation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "record_id")
    private UUID recordId = UUID.randomUUID();

    @Getter @Setter
    @Column(name = "record_def")
    private String recordDef;

    @Getter @Setter private LocalDateTime time;

    @Getter @Setter private int distance;

    @Getter @Setter private int power;

    @Getter @Setter private int cadence;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private Activity activity;

}
