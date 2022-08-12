package com.julio.learnSB.service;

import com.julio.learnSB.request.StudentRequest;
import com.julio.learnSB.response.StudentListResponse;
import com.julio.learnSB.response.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    StudentResponse getDetail(Integer id);

    List<StudentListResponse> getListStudent();

    String save(StudentRequest studentRequest);
}
