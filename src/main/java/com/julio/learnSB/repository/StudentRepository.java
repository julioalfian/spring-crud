package com.julio.learnSB.repository;

import com.julio.learnSB.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
