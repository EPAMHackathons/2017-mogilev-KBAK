package com.hackaton2017.repository;

import com.hackaton2017.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kanstantsin_Tolstsik on 4/8/2017.
 */
public interface GoalRepository extends JpaRepository<Goal, Long> {
}
