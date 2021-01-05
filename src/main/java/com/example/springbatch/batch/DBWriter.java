package com.example.springbatch.batch;


import com.example.springbatch.model.User;
import com.example.springbatch.repositories.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<User>
{
    @Autowired
    UserRepository userRepository;

    @Override
    public void write(List<? extends User> users) throws Exception
    {

        users.stream().forEach(x-> {
            userRepository.save(x);
        });
        //userRepository.save(users);
    }
}
