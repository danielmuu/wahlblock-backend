package de.devdanmu.wahlblockbackend.repository;

import de.devdanmu.wahlblockbackend.data.entity.SelectionOptions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author danmu
 * created on 2018-05-13
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SelectionOptionsRepositoryTest {

    @Autowired
    SelectionOptionsRepository selectionOptionsRepository;

    @Test
    public void findAllByElectionId() {
        // Given
        int electionId = 1;

        // When
        List<SelectionOptions> selectionOptions = selectionOptionsRepository.findAllByElectionId(electionId);

        // Then
        Assert.assertNotNull(selectionOptions);
        Assert.assertEquals(2, selectionOptions.size());
    }
}
