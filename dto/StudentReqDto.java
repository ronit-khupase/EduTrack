package com.practice.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudentReqDto {
    @NotBlank(message = "Name Cannot be Blank")
    private String name;

    @Email
    private String email;

    @Min(value = 18, message = "Age Cannot be Less Than 18")
    @Max(value = 100, message = "Age Cannot be Greater than 100")
    private Integer age;

    @NotBlank(message = "Subject Cannot be Blank")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
