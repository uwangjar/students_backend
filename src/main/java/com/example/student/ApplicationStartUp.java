package com.example.student;

import com.example.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApplicationStartUp implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    IStudentService iStudentService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {

            iStudentService.readFileFromResources("Student.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
