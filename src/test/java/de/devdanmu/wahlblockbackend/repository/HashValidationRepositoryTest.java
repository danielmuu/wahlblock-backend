package de.devdanmu.wahlblockbackend.repository;

import de.devdanmu.wahlblockbackend.data.entity.VoterHash;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author danmu
 * created on 2018-05-13
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class HashValidationRepositoryTest {

    @Autowired
    HashValidationRepository hashValidationRepository;

    @Autowired
    TestEntityManager entityManager;

    @Before
    public void setUp() {
        // fill DB with a hash
        VoterHash voterHash = new VoterHash();
        voterHash.setHash("ABC");
        entityManager.persist(voterHash);
        entityManager.flush();
    }

    @Test
    public void findByHashIn() {
        // Given
        List<String> hashListToFind = new ArrayList<>();
        hashListToFind.add("ABC");
        hashListToFind.add("DEF");

        // When
        List<VoterHash> hashListResult = hashValidationRepository.findByHashIn(hashListToFind);

        // Then
        Assert.assertEquals(1, hashListResult.size());
        Assert.assertEquals("ABC", hashListResult.get(0).getHash());
    }
}
