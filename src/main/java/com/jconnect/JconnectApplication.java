package com.jconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.jconnect.dao.UserRepository;
import com.jconnect.entities.User;

@SpringBootApplication
public class JconnectApplication {

    public static void main(final String[] args) {
        final ApplicationContext ctx = SpringApplication.run(JconnectApplication.class, args);
        final UserRepository userRepository = ctx.getBean(UserRepository.class);
//
//        final User user = new User();
//        user.setFirstName("admin");
//        user.setLastName("admin");
//        user.setEmail("admin@gmail.com");
//        user.setRole("ADMIN");
//        userRepository.save(user);
//
//       final User user2 = new User();
//        user2.setFirstName("ali");
//        user2.setLastName("ben saleh");
//        user2.setEmail("b-saleh@gmail.com");
//        userRepository.save(user2);
//
//        final User user3 = new User();
//        user3.setFirstName("mohamed");
//        user3.setLastName("ben Ali");
//        user3.setEmail("med_bAli@gmail.com");
//        userRepository.save(user3);
    }
}
