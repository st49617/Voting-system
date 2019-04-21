package cz.upce.votingsystemapplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Suggestion {

    // JUST FOR MEETING-RELATED PURPOSES

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
