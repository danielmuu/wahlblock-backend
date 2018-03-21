package de.devdanmu.wahlblockbackend.jpa;

import lombok.Data;

import java.util.List;

/**
 * @author danmu
 * created on 2018-03-21
 */
@Data
public class ElectionResponse extends Election {

    private List<SelectionOptions> selectionOptions;

    public ElectionResponse(final Election election) {
        this.setId(election.getId());
        this.setTitle(election.getTitle());
        this.setDescription(election.getDescription());
        this.setBeginDate(election.getBeginDate());
        this.setEndDate(election.getEndDate());
    }

}
