package de.devdanmu.wahlblockbackend.repository;

import de.devdanmu.wahlblockbackend.data.entity.Voter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author danmu
 * created on 2018-05-13
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class VoterRepositoryTest {

    @Autowired
    VoterRepository voterRepository;

    @Test
    public void should_returnVoter_when_loginVoterLoginDataIsFoundInDb () {
        // given
        String idCardNumber = "JFLMJJTLJ";
        String voterKey = "37191716";

        // when
        Voter voter = voterRepository.findFirstByIdCardNumberAndVoterKey(idCardNumber, voterKey);

        // then
        Assert.assertNotNull(voter);
        Assert.assertEquals(idCardNumber, voter.getIdCardNumber());
        Assert.assertEquals(voterKey, voter.getVoterKey());
        Assert.assertFalse(voter.isVoteSessionStarted());
    }
}
