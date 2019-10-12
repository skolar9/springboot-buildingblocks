package com.springboot.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restservices.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	User findByUserName(String userName);
}
