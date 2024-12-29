package com.abcode.springrefresh.repository;

import com.abcode.springrefresh.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlayerRepository extends JpaRepository<Player, Integer>, JpaSpecificationExecutor<Player> {
    Player findByName(String name);
}
