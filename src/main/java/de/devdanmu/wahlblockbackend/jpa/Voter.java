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
    private String idNumber;
    private String voterKey;
    private boolean voted;

    public Voter(int id, String name, String idNumber, String voterKey, boolean voted) {
        this.id = id;
        this.name = name;
        this.idNumber = idNumber;
        this.voterKey = voterKey;
        this.voted = voted;
    }

}
