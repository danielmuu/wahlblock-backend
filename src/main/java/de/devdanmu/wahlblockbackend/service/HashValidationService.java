package de.devdanmu.wahlblockbackend.service;

import de.devdanmu.wahlblockbackend.data.entity.VoterHash;
import de.devdanmu.wahlblockbackend.repository.HashValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> validateHashList(final List<String> hashStringList) {
        List<VoterHash> checkedValidHashes = matchStringHashListWithDB(hashStringList);
        List<VoterHash> voterHashList = getStringListAsVoterHashList(hashStringList);
        voterHashList.removeAll(checkedValidHashes); // this removes all valid hash values
        return getVoterHashListAsStringList(voterHashList);
    }

    private List<VoterHash> matchStringHashListWithDB(List<String> hashStringList) {
        return hashValidationRepository.findByHashIn(hashStringList);
    }

    private List<VoterHash> getStringListAsVoterHashList(List<String> hashStringList) {
            return hashStringList.stream().map(VoterHash::new).collect(Collectors.toList());
        }

        private List<String> getVoterHashListAsStringList(List<VoterHash> voterHashList) {
            return voterHashList.stream().map(VoterHash::getHash).collect(Collectors.toList());
        }
}
