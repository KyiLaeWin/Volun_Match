package com.volunteerManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.volunteerManagement.entity.Volunteer;

@Repository
public interface volunteerRepository extends JpaRepository<Volunteer,Long> {
	 Volunteer findByEmail(String email);

}
