package com.volunteerManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.volunteerManagement.entity.Event;
@Repository
public interface eventRepository extends JpaRepository<Event,Long>{

}
