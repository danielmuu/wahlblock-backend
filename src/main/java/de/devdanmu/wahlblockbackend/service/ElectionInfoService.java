package de.devdanmu.wahlblockbackend.service;

import de.devdanmu.wahlblockbackend.exception.ElectionNotFoundException;
import de.devdanmu.wahlblockbackend.jpa.Election;
import de.devdanmu.wahlblockbackend.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author danmu
 * created on 2018-03-17
 */
@Service
public class ElectionInfoService {

    private final ElectionRepository electionRepository;

    @Autowired
    public ElectionInfoService(final ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    public Election getElection() throws ElectionNotFoundException {
        Election election = electionRepository.findFirstBy();
        if (election != null)
            return election;
        else
            throw new ElectionNotFoundException();
    }
}
