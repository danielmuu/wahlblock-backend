package de.devdanmu.wahlblockbackend.repository;

import de.devdanmu.wahlblockbackend.jpa.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author danmu
 * created on 2018-03-12
 */
@Repository
@SuppressWarnings("ALL")
public interface VoterRepository extends JpaRepository<Voter, String>{

    @Override
    Voter save(Voter voter);

    @Override
    List<Voter> findAll();
}
