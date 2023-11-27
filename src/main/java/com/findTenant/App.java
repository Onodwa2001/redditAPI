package com.findTenant;

import com.findTenant.domain.Comment;
import com.findTenant.domain.Post;
import com.findTenant.domain.User;
import com.findTenant.domain.Vote;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController // testing
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }

    @GetMapping("hello-world")
    public String sayHello() {
        return "Hello World";
    }
}
