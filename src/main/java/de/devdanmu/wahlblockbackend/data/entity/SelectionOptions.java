package de.devdanmu.wahlblockbackend.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * @author danmu
 * created on 2018-03-21
 */
@Data
@Entity
@IdClass(SelectionOptionsPrimaryKey.class)
public class SelectionOptions implements Serializable {

    @Id
    @JsonIgnore
    private int electionId;

    @Id
    private int position;

    private String option;

}

@Data
class SelectionOptionsPrimaryKey implements Serializable {

    private int electionId;

    private int position;

}
