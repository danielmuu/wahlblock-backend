package de.devdanmu.wahlblockbackend.repository;

import de.devdanmu.wahlblockbackend.data.entity.SelectionOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author danmu
 * created on 2018-03-21
 */
@Repository
public interface SelectionOptionsRepository extends JpaRepository<SelectionOptions, String> {

    List<SelectionOptions> findAllByElectionId(int electionId);
}
