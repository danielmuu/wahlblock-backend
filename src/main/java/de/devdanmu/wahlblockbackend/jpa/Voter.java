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
    @SequenceGenerator(name = "voter_seq_generator", sequenceName = "voter_seq_generator", allocationSize = 1)
    private int id;

    private String name;

    private String idCardNumber;

    private Integer voterKey;

    private boolean voteSessionStarted;

}
