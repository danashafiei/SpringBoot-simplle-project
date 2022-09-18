package ir.nekron.sms.service;

import ir.nekron.sms.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student updateStudent(Student student);
    Student getStudentById(Long id);
    void deleteStudentById(Long id);
}
