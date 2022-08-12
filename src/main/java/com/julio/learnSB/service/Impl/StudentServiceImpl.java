package com.julio.learnSB.service.Impl;

import com.julio.learnSB.model.Student;
import com.julio.learnSB.repository.StudentRepository;
import com.julio.learnSB.request.StudentRequest;
import com.julio.learnSB.response.StudentListResponse;
import com.julio.learnSB.response.StudentResponse;
import com.julio.learnSB.service.StudentService;
import com.julio.learnSB.util.MessageLib;
import com.julio.learnSB.util.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    MessageLib messageLib;


    @Override
    public List<StudentListResponse> getListStudent() {
        List<Student> all = studentRepository.findAll();

        return all.stream().map(student -> StudentListResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .address(student.getAddress())
                .build()).collect(Collectors.toList());
    }

    @Override
    public StudentResponse getDetail(Integer id) {
        Student student = studentRepository.findById(id).orElse(new Student());

        return StudentResponse.builder()
                .address(student.getAddress())
                .name(student.getName())
                .build();
    }

    @Override
    public String save(StudentRequest studentRequest) {

        if (studentRequest.getName() == null){
            throw new BadRequestException(messageLib.saveStudentFailed() + ", field must be name");
        }
        if(studentRequest.getAddress() == null){
            throw new BadRequestException(messageLib.saveStudentFailed() + ", field must be address");
        }
        Student student = Student.builder()
                .name(studentRequest.getName())
                .address(studentRequest.getAddress())
                .build();

        studentRepository.save(student);
        return messageLib.getAddSuccess();
    }
}
