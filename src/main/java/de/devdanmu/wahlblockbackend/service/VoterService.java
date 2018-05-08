package de.devdanmu.wahlblockbackend.service;

import de.devdanmu.wahlblockbackend.data.entity.Voter;
import de.devdanmu.wahlblockbackend.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author danmu
 * created on 2018-03-15
 */
@Service
public class VoterService {

    private final VoterRepository voterRepository;

    @Autowired
    public VoterService(final VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    void updateVoterLoginStatus(Voter voter) {
        voter.setVoteSessionStarted(true);
        voterRepository.save(voter);
    }

}
