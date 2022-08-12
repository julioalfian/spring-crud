package com.julio.learnSB.controller.impl;

import com.julio.learnSB.controller.StudentController;
import com.julio.learnSB.model.Student;
import com.julio.learnSB.request.StudentRequest;
import com.julio.learnSB.response.BaseResponse;
import com.julio.learnSB.response.StudentResponse;
import com.julio.learnSB.service.StudentService;
import com.julio.learnSB.util.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class StudentControllerImpl implements StudentController {

    @Autowired
    StudentService studentService;

    @Override
    public ResponseEntity<BaseResponse> getDetail(Integer id) {
        return ResponseHelper.buildOkResponse(studentService.getDetail(id));
    }

    @Override
    public ResponseEntity<BaseResponse> StudentListResponse(){
        return ResponseHelper.buildOkResponse(studentService.getListStudent());
    }

    @Override
    public ResponseEntity<BaseResponse> save(StudentRequest studentRequest) {
        return ResponseHelper.buildOkResponse(studentService.save(studentRequest));
    }
}
