package com.practice.student.controller;

import com.practice.student.dto.StudentReqDto;
import com.practice.student.dto.StudentResDto;
import com.practice.student.entity.Student;
import com.practice.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<StudentResDto> createStudent(@RequestBody StudentReqDto studentReq){

        StudentResDto studentResDto = studentService.createStudent(studentReq);

        return ResponseEntity.status(201).body(studentResDto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StudentResDto> getStudent(@PathVariable Long id){

        StudentResDto studentResDto = studentService.getStudent(id);

        if (studentResDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(200).body(studentResDto);
    }

    @GetMapping("/get")
    public ResponseEntity<List<StudentResDto>> getAllStudents(){

        List<StudentResDto> students = studentService.getAllStudents();

        return ResponseEntity.status(200).body(students);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResDto> updateStudent(@PathVariable Long id,
                                                 @RequestBody StudentReqDto studentReqDto){

        StudentResDto studentResDto = studentService.updateStudent(id, studentReqDto);

        if(studentResDto == null){
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(studentResDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){

        boolean isDeleted = studentService.deleteStudent(id);

        return isDeleted ? ResponseEntity.status(200).body("Student Deleted Successfully") : ResponseEntity.status(404).body("Student Not Exist");
    }

    @PatchMapping("delete-soft/{id}")
    public ResponseEntity<String> deleteSoftly(@PathVariable Long id){

        boolean deleted = studentService.deleteSoftly(id);

        if(!deleted){
            return ResponseEntity.status(404).body("Student Not Found!");
        }
        return ResponseEntity.status(200).body("Student Deleted!");
    }
}
