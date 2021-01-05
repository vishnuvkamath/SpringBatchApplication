package com.example.springbatch.repositories;

import com.example.springbatch.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
}
