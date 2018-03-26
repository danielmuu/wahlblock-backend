package de.devdanmu.wahlblockbackend.service;

import de.devdanmu.wahlblockbackend.data.entity.VoterHash;
import de.devdanmu.wahlblockbackend.repository.HashValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author danmu
 * created on 2018-03-20
 */
@Service
public class HashValidationService {

    private final HashValidationRepository hashValidationRepository;

    @Autowired
    public HashValidationService(final HashValidationRepository hashValidationRepository) {
        this.hashValidationRepository = hashValidationRepository;
    }

    public List<String> validateHashList(final List<String> hashListToValidate) {
        List<VoterHash> voterHashListToValidate = getListWithVoterHash(hashListToValidate);
        List<VoterHash> foundValidHashes = hashValidationRepository.findByHashIn(hashListToValidate);
        voterHashListToValidate.removeAll(foundValidHashes); // removes all valid hash values
        return getListWithStrings(voterHashListToValidate);
    }

    private List<VoterHash> getListWithVoterHash(List<String> hashListToValidate) {
        List<VoterHash> voterHashListToValidate = new ArrayList<>();
        for(String hash : hashListToValidate) {
            voterHashListToValidate.add(new VoterHash(hash));
        }
        return voterHashListToValidate;
    }

    private List<String> getListWithStrings(List<VoterHash> voterHashListToValidate) {
        List<String> hashListWithNonValidHashes = new ArrayList<>();
        for(VoterHash voterHash : voterHashListToValidate) {
            hashListWithNonValidHashes.add(voterHash.getHash());
        }
        return hashListWithNonValidHashes;
    }
}
