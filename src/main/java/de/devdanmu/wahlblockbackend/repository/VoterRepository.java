package de.devdanmu.wahlblockbackend.repository;

import de.devdanmu.wahlblockbackend.jpa.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author danmu
 * created on 2018-03-12
 */
@SuppressWarnings("unchecked")
@Repository
public interface VoterRepository extends JpaRepository<Voter, String> {

    @Override
    Voter save(Voter voter);

    Voter findFirstByIdCardNumberAndVoterKey(String idCardNumber, Integer voterKey);
}
