package de.devdanmu.wahlblockbackend.repository;

import de.devdanmu.wahlblockbackend.data.entity.VoterHash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author danmu
 * created on 2018-03-20
 */
@Repository
public interface HashValidationRepository extends JpaRepository<VoterHash, String> {

    List<VoterHash> findByHashIn(List<String> hashList);
}
