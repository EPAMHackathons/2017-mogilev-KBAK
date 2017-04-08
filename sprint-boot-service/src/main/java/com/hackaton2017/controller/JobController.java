package com.hackaton2017.controller;

import com.hackaton2017.domain.Job;
import com.hackaton2017.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Created by Kanstantsin_Tolstsik on 4/4/2017.
 */
@RestController
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    ResponseEntity<Iterable<Job>> getJobByStatus(@RequestParam(name = "completed", required = false) Boolean isCompleted) {
        final Iterable<Job> jobs = isCompleted != null ? jobRepository.findAllByIsCompleted(isCompleted) : jobRepository.findAll();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.POST)
    ResponseEntity<?> createJob(@RequestBody Job job) {
        final Job createdJob = jobRepository.save(job);
        final HttpHeaders responseHeaders = new HttpHeaders();
        final URI createdJobURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdJob.getId()).toUri();
        responseHeaders.setLocation(createdJobURI);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
}