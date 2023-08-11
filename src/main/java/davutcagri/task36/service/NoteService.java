package davutcagri.task36.service;

import davutcagri.task36.dto.LessonDTO;
import davutcagri.task36.dto.NoteDTO;
import davutcagri.task36.dto.StudentDTO;
import davutcagri.task36.model.Lesson;
import davutcagri.task36.model.Note;
import davutcagri.task36.model.Student;
import davutcagri.task36.repository.LessonRepository;
import davutcagri.task36.repository.NoteRepository;
import davutcagri.task36.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;

    public NoteService(NoteRepository noteRepository, LessonRepository lessonRepository, StudentRepository studentRepository) {
        this.noteRepository = noteRepository;
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
    }

    public NoteDTO save(Note note) {
        note.setAverageNote((note.getMidTermNote() + note.getFinalNote()) / 2);
        noteRepository.save(note);

        Student student = studentRepository.findById(note.getStudentId()).get();
        student.getNoteIds().add(note.getNoteId());
        studentRepository.save(student);
        Lesson lesson = lessonRepository.findById(note.getLessonId()).get();
        lesson.getNoteIds().add(note.getNoteId());
        lessonRepository.save(lesson);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLessonName(lesson.getLessonName());

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setLesson(lessonDTO);
        noteDTO.setStudent(studentDTO);
        noteDTO.setMidTermNote(note.getMidTermNote());
        noteDTO.setFinalNote(note.getFinalNote());
        noteDTO.setAverageNote(note.getAverageNote());
        return noteDTO;
    }

    public List<NoteDTO> findAll() {
        return noteRepository.findAll().stream().map(note -> {
            Student student = studentRepository.findById(note.getStudentId()).get();
            Lesson lesson = lessonRepository.findById(note.getLessonId()).get();

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName(student.getFirstName());
            studentDTO.setLastName(student.getLastName());
            studentDTO.setEmail(student.getEmail());

            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setLessonName(lesson.getLessonName());

            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setLesson(lessonDTO);
            noteDTO.setStudent(studentDTO);
            noteDTO.setMidTermNote(note.getMidTermNote());
            noteDTO.setFinalNote(note.getFinalNote());
            noteDTO.setAverageNote(note.getAverageNote());
            return noteDTO;
        }).collect(Collectors.toList());
    }

    public NoteDTO findNoteById(String noteId) {
        Note note = noteRepository.findById(noteId).get();

        Student student = studentRepository.findById(note.getStudentId()).get();
        Lesson lesson = lessonRepository.findById(note.getLessonId()).get();

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLessonName(lesson.getLessonName());

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setLesson(lessonDTO);
        noteDTO.setStudent(studentDTO);
        noteDTO.setMidTermNote(note.getMidTermNote());
        noteDTO.setFinalNote(note.getFinalNote());
        noteDTO.setAverageNote(note.getAverageNote());
        return noteDTO;
    }

}
