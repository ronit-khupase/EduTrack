package com.practice.student.service;

import com.practice.student.entity.Student;
import com.practice.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student studentReq){
        return studentRepository.save(studentReq);
    }

    public Optional<Student> getStudent(Long id){
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, Student student){

        Optional<Student> existedStudent = studentRepository.findById(id);

        if(existedStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existedStudent.get();

        studentToSave.setName(student.getName());
        studentToSave.setAge(student.getAge());
        studentToSave.setEmail(student.getEmail());
        studentToSave.setSubject(student.getSubject());

        return studentRepository.save(studentToSave);
    }

    public boolean deleteStudent(Long id) {

        boolean exists = studentRepository.existsById(id);

        if(!exists){
            return false;
        }

        studentRepository.deleteById(id);

        return true;
    }
}
