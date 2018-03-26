package de.devdanmu.wahlblockbackend.service;

import de.devdanmu.wahlblockbackend.exception.ElectionNotFoundException;
import de.devdanmu.wahlblockbackend.data.entity.Election;
import de.devdanmu.wahlblockbackend.data.ElectionResponse;
import de.devdanmu.wahlblockbackend.data.entity.SelectionOptions;
import de.devdanmu.wahlblockbackend.repository.ElectionRepository;
import de.devdanmu.wahlblockbackend.repository.SelectionOptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author danmu
 * created on 2018-03-17
 */
@Service
public class ElectionInfoService {

    private final ElectionRepository electionRepository;
    private final SelectionOptionsRepository selectionOptionsRepository;

    @Autowired
    public ElectionInfoService(final ElectionRepository electionRepository, final SelectionOptionsRepository selectionOptionsRepository) {
        this.electionRepository = electionRepository;
        this.selectionOptionsRepository = selectionOptionsRepository;
    }

    public ElectionResponse getElection(final int electionId) throws ElectionNotFoundException {
        Election election = electionRepository.findFirstById(electionId);
        List<SelectionOptions> options = selectionOptionsRepository.findAllByElectionId(electionId);
        if (election != null && options.size() > 0)
            return buildElectionResponse(election, options);
        else
            throw new ElectionNotFoundException();
    }

    private ElectionResponse buildElectionResponse(final Election election, final List<SelectionOptions> options) {
        ElectionResponse response = new ElectionResponse(election);
        response.setSelectionOptions(options);
        return response;
    }
}
