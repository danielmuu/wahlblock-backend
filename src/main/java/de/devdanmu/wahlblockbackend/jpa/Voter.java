package de.devdanmu.wahlblockbackend.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * @author danmu
 * created on 2018-03-12
 */
@Entity
@Data
public class Voter {

    @Id
    private String idCardNumber;

    private Integer voterKey;

    private boolean voteSessionStarted;

}
