package org.rubik.sandbox.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.base.Throwables;

public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:/spring/app-batch.xml"});
		JobLauncher jobLauncher = (JobLauncher) ac.getBean("jobLauncher");

		Job playJob = (Job) ac.getBean("playJob");
		try {
            JobExecution playResult = jobLauncher.run(playJob, new JobParameters());
            System.out.println(playResult.toString());
        } catch (Exception e) {
            Throwables.propagate(e);
        }

		Job mediaJob = (Job) ac.getBean("mediaJob");
		try {
            JobExecution mediaResult = jobLauncher.run(mediaJob, new JobParameters());
            System.out.println(mediaResult.toString());
        } catch (Exception e) {
            Throwables.propagate(e);
        }
	}
}