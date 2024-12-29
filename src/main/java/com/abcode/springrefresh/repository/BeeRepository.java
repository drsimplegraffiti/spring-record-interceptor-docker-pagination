package com.abcode.springrefresh.repository;

import com.abcode.springrefresh.model.Bee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeeRepository extends JpaRepository<Bee, Long> {
}