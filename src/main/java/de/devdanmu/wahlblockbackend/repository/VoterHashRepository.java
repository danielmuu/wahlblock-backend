package de.devdanmu.wahlblockbackend.repository;

import de.devdanmu.wahlblockbackend.jpa.VoterHash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author danmu
 * created on 2018-03-15
 */
@SuppressWarnings("unchecked")
@Repository
public interface VoterHashRepository extends JpaRepository<VoterHash, String> {

    @Override
    VoterHash save(VoterHash voterHash);

}
