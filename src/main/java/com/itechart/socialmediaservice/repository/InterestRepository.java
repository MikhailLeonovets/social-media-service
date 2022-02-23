package com.itechart.socialmediaservice.repository;

import com.itechart.socialmediaservice.repository.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {
}
