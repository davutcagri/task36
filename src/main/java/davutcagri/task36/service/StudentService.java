package davutcagri.task36.service;

import davutcagri.task36.dto.LessonDTO;
import davutcagri.task36.dto.NoteDTO;
import davutcagri.task36.dto.StudentDTO;
import davutcagri.task36.model.Lesson;
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
            studentDTO.setNotes(noteRepository.findAllById(student.getNoteIds()).stream().map(note -> {
                Lesson lesson = lessonRepository.findById(note.getLessonId()).get();

                LessonDTO lessonDTO = new LessonDTO();
                lessonDTO.setLessonName(lesson.getLessonName());

                NoteDTO noteDTO = new NoteDTO();
                noteDTO.setLesson(lessonDTO);
                noteDTO.setMidTermNote(note.getMidTermNote());
                noteDTO.setFinalNote(note.getFinalNote());
                noteDTO.setAverageNote(note.getAverageNote());
                return noteDTO;
            }).collect(Collectors.toList()));
            return studentDTO;
        }).collect(Collectors.toList());
    }

    public StudentDTO findStundentById(String studentId) {
        Student student = studentRepository.findById(studentId).get();
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setNotes(noteRepository.findAllById(student.getNoteIds()).stream().map(note -> {
            Lesson lesson = lessonRepository.findById(note.getLessonId()).get();

            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setLessonName(lesson.getLessonName());

            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setLesson(lessonDTO);
            noteDTO.setMidTermNote(note.getMidTermNote());
            noteDTO.setFinalNote(note.getFinalNote());
            noteDTO.setAverageNote(note.getAverageNote());
            return noteDTO;
        }).collect(Collectors.toList()));
        return studentDTO;
    }

}
