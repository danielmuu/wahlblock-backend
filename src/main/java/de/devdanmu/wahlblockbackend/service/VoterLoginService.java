package de.devdanmu.wahlblockbackend.service;

import de.devdanmu.wahlblockbackend.data.VoterLogin;
import de.devdanmu.wahlblockbackend.data.entity.Voter;
import de.devdanmu.wahlblockbackend.data.entity.VoterHash;
import de.devdanmu.wahlblockbackend.exception.NotAVoterException;
import de.devdanmu.wahlblockbackend.exception.VoterLoggedInException;
import de.devdanmu.wahlblockbackend.repository.VoterHashRepository;
import de.devdanmu.wahlblockbackend.repository.VoterRepository;
import de.devdanmu.wahlblockbackend.util.SecretUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author danmu
 * created on 2018-03-15
 */
@Service
public class VoterLoginService {

    private final VoterService voterService;
    private final VoterRepository voterRepository;
    private final VoterHashRepository voterHashRepository;

    @Autowired
    public VoterLoginService(final VoterService voterService,
                             final VoterRepository voterRepository,
                             final VoterHashRepository voterHashRepository) {
        this.voterService = voterService;
        this.voterRepository = voterRepository;
        this.voterHashRepository = voterHashRepository;
    }

    public VoterHash startVoterLogin(final VoterLogin voterLogin) throws Exception {
        Voter voter = voterRepository.findFirstByIdCardNumberAndVoterKey(voterLogin.getIdCardNumber(), voterLogin.getVoterKey());
        if (checkVotersVotePermission(voter)) {
            VoterHash voterHash = getVoterHash(voterLogin.getPublicKey());
            if (!StringUtils.isEmpty(voterHash.getHash())) {
                voterHashRepository.save(voterHash);
                voterService.updateVoterLoginStatus(voter);
                return voterHash;
            } else {
                throw new Exception("Generating hash for voter error");
            }
        }
        return null;
    }

    private VoterHash getVoterHash(final String publicKey) {
        String hash = SecretUtil.getSha256Hash(publicKey);
        return new VoterHash(hash);
    }

    private boolean checkVotersVotePermission(final Voter voter) throws Exception {
        if (voter == null) {
            throw new NotAVoterException("Voting not allowed");
        } else if (voter.isVoteSessionStarted()) {
            throw new VoterLoggedInException("Voter logged in before");
        } else {
            return true; // should be a valid voter
        }
    }
}
