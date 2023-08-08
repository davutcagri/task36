package davutcagri.task36.service;

import davutcagri.task36.dto.LessonDTO;
import davutcagri.task36.dto.NoteDTO;
import davutcagri.task36.model.Lesson;
import davutcagri.task36.model.Note;
import davutcagri.task36.model.Student;
import davutcagri.task36.repository.LessonRepository;
import davutcagri.task36.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;

    public LessonService(LessonRepository lessonRepository, StudentRepository studentRepository) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
    }

    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    public List<LessonDTO> findAll() {
        return lessonRepository.findAll().stream().map(lesson -> {

            List<Student> studentList = new ArrayList<>();
            for (Student student : studentList) {
                studentList.add(student);
            }

            List<Note> noteList = new ArrayList<>();
            for (Note note : noteList) {
                noteList.add(note);
            }

            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setName(lesson.getName());
            lessonDTO.setTeacher(lesson.getTeacher());
            lessonDTO.setStudentNames(studentList.stream().map(Student::getName).collect(Collectors.toList()));
            lessonDTO.setNoteDTOs(noteList.stream().map(note -> {
                NoteDTO noteDTO = new NoteDTO();
                noteDTO.setMark(note.getMark());
                noteDTO.setLessonName(lesson.getName());
                noteDTO.setStudentName(studentRepository.getStudentById(note.getStudentId()).getName());
                return noteDTO;
            }).collect(Collectors.toList()));
            return lessonDTO;
        }).collect(Collectors.toList());
    }

}
