package ir.nekron.sms.controller;


import ir.nekron.sms.entity.Student;
import ir.nekron.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    //handler method to handle list students and return model and view
    @GetMapping("/students")
    public String listStudents(Model model){
        model.addAttribute("result", studentService.getAllStudents());
        return "students";

    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("add_student", student);
        return "create_student";

    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("add_student") Student student){
        studentService.saveStudent(student);
        return "redirect:/students";

    }

    @GetMapping("/students/edit/{id}")
    public String updateStudentForm(@PathVariable Long id, Model model){
        model.addAttribute("update_student", studentService.getStudentById(id));
        return "edit_student";

    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("update_student") Student student){
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setCode(student.getCode());

        studentService.updateStudent(existingStudent);

        return "redirect:/students";


    }

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return "redirect:/students";

    }
}
