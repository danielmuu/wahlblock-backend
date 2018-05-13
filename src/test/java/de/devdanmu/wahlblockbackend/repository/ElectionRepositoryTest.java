package de.devdanmu.wahlblockbackend.repository;

import de.devdanmu.wahlblockbackend.data.entity.Election;
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
public class ElectionRepositoryTest {

    @Autowired
    ElectionRepository electionRepository;

    @Test
    public void findFirstById() {
        // Given
        int electionId = 1;

        // When
        Election election = electionRepository.findFirstById(electionId);

        // Then
        Assert.assertNotNull(election);
        Assert.assertEquals(election.getTitle(), "Sind sie für ein Verbot von Haustierprofilen auf Facebook?");
    }
}
