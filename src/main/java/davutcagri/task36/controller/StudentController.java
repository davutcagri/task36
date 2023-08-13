package davutcagri.task36.controller;

import davutcagri.task36.dto.StudentDTO;
import davutcagri.task36.model.Student;
import davutcagri.task36.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public StudentDTO save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @GetMapping("/findall")
    public List<StudentDTO> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/get-one/{studentId}")
    public StudentDTO findStudentById(@PathVariable String studentId) {
        return studentService.findStundentById(studentId);
    }

    @GetMapping("/get-passed-student")
    public List<StudentDTO> findPassedStudentsByNote() {
        return studentService.findPassedStudentsByNote();
    }

    @GetMapping("/get-failed-student")
    public List<StudentDTO> findFailedStudentsByNote() {
        return studentService.findFailedStudentsByNote();
    }


}
