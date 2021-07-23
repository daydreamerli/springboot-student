package com.example.demo.student;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentReposity;

    @Autowired
    public StudentService(StudentRepository studentReposity){

        this.studentReposity = studentReposity;
    }

    public List<Student> getStudents() {
        
        return studentReposity.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional =
         studentReposity.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentReposity.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean studentExist =studentReposity.existsById(studentId);
        if(!studentExist){
            throw new IllegalStateException(
                    "Student with id: "+studentId +" does not exists"
            );
        }
        studentReposity.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentReposity.findById(studentId)
                .orElseThrow(()-> new IllegalStateException(
                        "Student with id: "+studentId +" does not found"
                ));
        if(name != null && name.length()>3 &&
                !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email != null && email.length()>3 && !email.contains("@") &&
                !Objects.equals(student.getEmail(),email)){
            student.setEmail(email);
        }

    }
}
