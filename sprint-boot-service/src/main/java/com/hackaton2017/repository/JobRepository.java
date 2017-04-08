package com.hackaton2017.repository;

import com.hackaton2017.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Kanstantsin_Tolstsik on 4/4/2017.
 */
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findAllByIsCompleted(final Boolean isCompleted);
}
