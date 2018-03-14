package de.devdanmu.wahlblockbackend.jpa;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author danmu
 * created on 2018-03-14
 */

@Entity
@Data
public class Election {

    @Id
    private int id;
    private String title;
    private String description;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
}
