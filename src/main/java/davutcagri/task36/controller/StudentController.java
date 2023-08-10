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

    @PutMapping("/add/lesson/{studentId}")
    public StudentDTO addLesson(@PathVariable String studentId, @RequestBody String lessonName) {
        return studentService.addLesson(studentId, lessonName);
    }
}
