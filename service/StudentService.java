package com.practice.student.service;

import com.practice.student.dto.StudentReqDto;
import com.practice.student.dto.StudentResDto;
import com.practice.student.entity.Student;
import com.practice.student.repository.StudentRepository;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


    public StudentResDto createStudent(StudentReqDto studentReq){
        Student student = mapToEntity(studentReq);

        Student studentRes = studentRepository.save(student);

        return mapToDto(studentRes);
    }

    public StudentResDto getStudent(Long id){
        Optional<Student> student = studentRepository.findByIdAndIsDeletedFalse(id);

        return student.map(this::mapToDto).orElse(null);

    }

    public List<StudentResDto> getAllStudents(){

        List<StudentResDto> studentRes = new ArrayList<>();

        for(Student student : studentRepository.findByIsDeletedFalse()){
            studentRes.add(mapToDto(student));
        }

        return studentRes;
    }

    public StudentResDto updateStudent(Long id, StudentReqDto studentReqDto){

        Optional<Student> existedStudent = studentRepository.findByIdAndIsDeletedFalse(id);

        if(existedStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existedStudent.get();

        studentToSave.setName(studentReqDto.getName());
        studentToSave.setAge(studentReqDto.getAge());
        studentToSave.setEmail(studentReqDto.getEmail());
        studentToSave.setSubject(studentReqDto.getSubject());

        return mapToDto(studentRepository.save(studentToSave));
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

        Optional<Student> student = studentRepository.findByIdAndIsDeletedFalse(id);

        if(student.isEmpty()){
            return false;
        }
        Student studentToSave = student.get();

        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);

        return true;
    }

    public Student mapToEntity(StudentReqDto studentReqDto){
        Student student = new Student();
        student.setName(studentReqDto.getName());
        student.setEmail(studentReqDto.getEmail());
        student.setAge(studentReqDto.getAge());
        student.setSubject(studentReqDto.getSubject());
        student.setDeleted(false);

        return student;
    }

    public StudentResDto mapToDto(Student student){
        StudentResDto studentResDto = new StudentResDto();
        studentResDto.setName(student.getName());
        studentResDto.setEmail(student.getEmail());
        studentResDto.setAge(student.getAge());
        studentResDto.setSubject(student.getSubject());
        studentResDto.setId(student.getId());

        return studentResDto;
    }
}
