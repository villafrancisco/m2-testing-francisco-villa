package com.m2testingfranciscovilla.m2testingfranciscovilla.controller;

import com.m2testingfranciscovilla.m2testingfranciscovilla.entities.Student;
import com.m2testingfranciscovilla.m2testingfranciscovilla.repository.StudentRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {


    StudentRepositoryI studentRepository;

    public StudentController(StudentRepositoryI studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> findAll(){

        return studentRepository.findAll();
    }
    @GetMapping("/data")
    public void demoData(){
        Student student1= new Student(null,"77777777M","Francisco Villa","Matematicas",8.6);
        Student student2= new Student(null,"99999999L","Maria Perez","Lenguaje",7.1);
        studentRepository.save(student1);
        studentRepository.save(student2);
    }
    @PostMapping("/students")
    public void create(@RequestBody Student student){
        if (student.getId()==null){
            studentRepository.save(student);
        }

    }
    @PutMapping("/students")
    public void update(@RequestBody Student student){
        if (student.getId()!=null && studentRepository.existsById(student.getId())){
            studentRepository.save(student);
        }
    }
    @DeleteMapping("/students/{id}")
    public void deleteById(@PathVariable Long id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
        }

    }
}
