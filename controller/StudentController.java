package com.practice.student.controller;

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
    public ResponseEntity<Student> createStudent(@RequestBody Student studentReq){

        Student studentRes = studentService.createStudent(studentReq);

        return ResponseEntity.status(201).body(studentRes);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){

        Optional<Student> studentRes = studentService.getStudent(id);

        return studentRes.map(student -> ResponseEntity.status(200).body(student)).orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Student>> getAllStudents(){

        List<Student> students = studentService.getAllStudents();

        return ResponseEntity.status(200).body(students);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestBody Student studentReq){

        Student studentRes = studentService.updateStudent(id, studentReq);

        if(studentReq == null){
            return ResponseEntity.status(404).body(null);
        }

        return ResponseEntity.status(200).body(studentRes);
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
