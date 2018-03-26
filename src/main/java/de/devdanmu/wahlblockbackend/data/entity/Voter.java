package de.devdanmu.wahlblockbackend.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

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
