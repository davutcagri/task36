package davutcagri.task36.service;

import davutcagri.task36.dto.StudentDTO;
import davutcagri.task36.model.Lesson;
import davutcagri.task36.model.Note;
import davutcagri.task36.model.Student;
import davutcagri.task36.repository.LessonRepository;
import davutcagri.task36.repository.NoteRepository;
import davutcagri.task36.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final NoteRepository noteRepository;

    public StudentService(StudentRepository studentRepository, LessonRepository lessonRepository, NoteRepository noteRepository) {
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
        this.noteRepository = noteRepository;
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(student -> {

            List<Lesson> lessonList = new ArrayList<>();
            for (String lessonId : student.getLessonIds()) {
                Lesson lesson = lessonRepository.getLessonById(lessonId);
                lessonList.add(lesson);
            }

            List<Note> noteList = new ArrayList<>();
            for (String noteId : student.getNoteIds()) {
                Note note = noteRepository.findNoteById(noteId);
                noteList.add(note);
            }

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(student.getName());
            studentDTO.setLessonNames(lessonList.stream().map(Lesson::getName).collect(Collectors.toList()));
            studentDTO.setNoteMarks(noteList.stream().map(Note::getMark).collect(Collectors.toList()));
            return studentDTO;
        }).collect(Collectors.toList());
    }

    public StudentDTO addLesson(String studentId, String lessonName) {
        System.out.println(lessonName);
        try {
            Student studentInDB = studentRepository.getStudentById(studentId);
            Lesson lessonInDB = lessonRepository.getLessonByName(lessonName);

            studentInDB.getLessonIds().add(lessonInDB.getId());
            studentRepository.save(studentInDB);

            List<Lesson> lessonList = new ArrayList<>();
            for (String lessonId : studentInDB.getLessonIds()) {
                Lesson lesson = lessonRepository.getLessonById(lessonId);
                lessonList.add(lesson);
            }

            List<Note> noteList = new ArrayList<>();
            for (String noteId : studentInDB.getNoteIds()) {
                Note note = noteRepository.findNoteById(noteId);
                noteList.add(note);
            }

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(studentInDB.getName());
            studentDTO.setLessonNames(lessonList.stream().map(Lesson::getName).collect(Collectors.toList()));
            studentDTO.setNoteMarks(noteList.stream().map(Note::getMark).collect(Collectors.toList()));
            return studentDTO;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
