package com.demo.jpa.JPADemo;

import com.demo.jpa.JPADemo.entity.User;
import com.demo.jpa.JPADemo.service.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDAOServiceCommandLineRunner  implements CommandLineRunner {


    private static final Logger log = LoggerFactory.getLogger(UserDAOServiceCommandLineRunner.class);

    @Autowired
    private UserDAO userDAO;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Jack", "Admin");
        long id = userDAO.insert(user);

        log.info("New user is created "+user +" "+id);
    }




}
