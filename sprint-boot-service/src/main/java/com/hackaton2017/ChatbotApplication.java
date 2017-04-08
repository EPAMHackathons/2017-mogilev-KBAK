package com.hackaton2017;

import com.hackaton2017.domain.*;
import com.hackaton2017.repository.GoalRepository;
import com.hackaton2017.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ChatbotApplication {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private GoalRepository goalRepository;

    private static final Logger log = LoggerFactory.getLogger(ChatbotApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(ChatbotApplication.class, args);
        log.info("Application started!");
    }

    @Bean
    CommandLineRunner runner () {
        return args -> {
            final Job job1 = new Job();
            job1.setJobType(JobType.GETTING_ITEM_DETAILS);
            job1.setShop(Shop.WILDBERRIES);
            job1.setUrl("http://example1.com");
            job1.setCompleted(Boolean.FALSE);
            final Job job2 = new Job();
            job2.setJobType(JobType.GETTING_ITEM_DETAILS);
            job2.setShop(Shop.EBAY);
            job2.setUrl("http://example2.com");
            job2.setCompleted(Boolean.FALSE);
            jobRepository.save(job1);
            jobRepository.save(job2);

            Goal goal1 = new Goal();
            goal1.setGoalType(GoalType.SIZE_WAITING);
            goal1.setData("L");
            goal1.setJob(job2);
            goalRepository.save(goal1);
            Goal goal2 = new Goal();
            goal2.setGoalType(GoalType.SIZE_WAITING);
            goal2.setData("XL");
            goal2.setJob(job1);
            goalRepository.save(goal2);
//            jobRepository.findAll().forEach(item -> {
//                log.info(item.toString());
//            });
        };
    }
}
