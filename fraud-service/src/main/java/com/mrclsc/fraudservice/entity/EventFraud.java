package com.mrclsc.fraudservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity(name = "EVENT_FRAUD")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventFraud implements Serializable {

    @Id
    @SequenceGenerator(
            name = "event_id_sequence",
            sequenceName = "event_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_id_sequence"
    )
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;

}
