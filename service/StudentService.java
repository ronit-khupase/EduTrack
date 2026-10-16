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
        studentReq.setDeleted(false);
        return studentRepository.save(studentReq);
    }

    public Optional<Student> getStudent(Long id){
        return studentRepository.findByIdAndIsDeletedFalse(id);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findByIsDeletedFalse();
    }

    public Student updateStudent(Long id, Student student){

        Optional<Student> existedStudent = studentRepository.findByIdAndIsDeletedFalse(id);

        if(existedStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existedStudent.get();

        studentToSave.setName(student.getName());
        studentToSave.setAge(student.getAge());
        studentToSave.setEmail(student.getEmail());
        studentToSave.setSubject(student.getSubject());
        studentToSave.setDeleted(false);

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

    public boolean deleteSoftly(Long id){

        Optional<Student> student = studentRepository.findById(id);

        if(student.isEmpty()){
            return false;
        }
        Student studentToSave = student.get();

        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);

        return true;
    }
}
