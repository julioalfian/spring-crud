package com.julio.learnSB.controller;

import com.julio.learnSB.model.Student;
import com.julio.learnSB.request.StudentRequest;
import com.julio.learnSB.response.BaseResponse;
import com.julio.learnSB.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/student")
public interface StudentController {

    @GetMapping("/getAll")
    public ResponseEntity<BaseResponse> StudentListResponse();


    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getDetail(@PathVariable Integer id);

    @PostMapping("/add")
    public ResponseEntity<BaseResponse> save(@RequestBody StudentRequest studentRequest);

}
