package com.itechart.socialmediaservice.repository;

import com.itechart.socialmediaservice.repository.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
