package com.m2testingfranciscovilla.m2testingfranciscovilla.repository;

import com.m2testingfranciscovilla.m2testingfranciscovilla.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryI extends JpaRepository<Student,Long> {
}
