package de.devdanmu.wahlblockbackend.data.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author danmu
 * created on 2018-03-15
 */
@Entity
@Data
public class VoterHash {

    @Id
    private String hash;

    public VoterHash(String hash) {
        this.hash = hash;
    }

    public VoterHash() {
    }
}
