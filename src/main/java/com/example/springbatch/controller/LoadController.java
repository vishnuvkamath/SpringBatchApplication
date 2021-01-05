package com.example.springbatch.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class LoadController
{
    @Autowired
    JobLauncher jobLauncher; //joblauncher is created by spring boot framework itself

    @Autowired
    Job job; // we have created the job in config class

    @GetMapping
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String,JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis())); // to pass time as param
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters); //to run the job we created

        System.out.println("Job Execution : " + jobExecution.getStatus()); // to get the job status
        System.out.println("Batch is Running...");

        while (jobExecution.isRunning())
        {
            System.out.println("...");
        }
        return jobExecution.getStatus();
    }
}
