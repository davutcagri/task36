package davutcagri.task36.service;

import davutcagri.task36.dto.NoteDTO;
import davutcagri.task36.dto.StudentDTO;
import davutcagri.task36.model.Student;
import davutcagri.task36.repository.LessonRepository;
import davutcagri.task36.repository.NoteRepository;
import davutcagri.task36.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final NoteRepository noteRepository;
    private final LessonRepository lessonRepository;

    public StudentService(StudentRepository studentRepository, NoteRepository noteRepository, LessonRepository lessonRepository) {
        this.studentRepository = studentRepository;
        this.noteRepository = noteRepository;
        this.lessonRepository = lessonRepository;
    }

    public StudentDTO save(Student student) {
        studentRepository.save(student);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        return studentDTO;
    }

    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(student -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName(student.getFirstName());
            studentDTO.setLastName(student.getLastName());
            studentDTO.setEmail(student.getEmail());
            return studentDTO;
        }).collect(Collectors.toList());
    }

    public StudentDTO addLesson(String studentId, String lessonName) {
        return null;
    }

}
