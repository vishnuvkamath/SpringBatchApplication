package com.example.springbatch.batch;

import com.example.springbatch.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<User, User>
{
    private static final Map<String,String> DEPT_NAMES = new HashMap<>();

    public Processor()
    {
        DEPT_NAMES.put("001","Technology");
        DEPT_NAMES.put("002","Basic Science");
        DEPT_NAMES.put("003","History");
    }

    @Override
    public User process(User user) throws Exception
    {
        String dept_code = user.getDept();
        String dept = DEPT_NAMES.get(dept_code);
        System.out.println(String.format("Converted from [%s] [%s]",dept_code,dept));
        user.setDept(dept); //seting the dept name to the dept code
        user.setTime(new Date());
        return user;
    }
}
