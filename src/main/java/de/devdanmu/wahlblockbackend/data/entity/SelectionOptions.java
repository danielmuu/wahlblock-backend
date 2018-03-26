package de.devdanmu.wahlblockbackend.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author danmu
 * created on 2018-03-21
 */
@Entity
@Data
public class SelectionOptions {

    @Id
    @JsonIgnore
    private int id;

    @JsonIgnore
    private int electionId; // todo make electionId and position primary key

    private int position;

    private String option;
}
