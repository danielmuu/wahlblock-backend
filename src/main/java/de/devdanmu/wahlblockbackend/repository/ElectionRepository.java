package de.devdanmu.wahlblockbackend.repository;

import de.devdanmu.wahlblockbackend.jpa.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author danmu
 * created on 2018-03-14
 */
@Repository
public interface ElectionRepository extends JpaRepository<Election, String> {

    Election findFirstById(int id);
}

