package com.demo.jpa.JPADemo;

import com.demo.jpa.JPADemo.entity.User;
import com.demo.jpa.JPADemo.service.UserDAO;
import com.demo.jpa.JPADemo.service.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner {


    private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Jill", "Admin");
        userRepository.save(user);
        log.info("New user is created "+user);

        Optional<User> userWithId1 =  userRepository.findById(1L);
        log.info("User retrieved" + userWithId1);

        List<User> userList = userRepository.findAll();
        log.info("User list "+userList);

    }




}
