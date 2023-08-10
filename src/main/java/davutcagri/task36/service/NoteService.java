package davutcagri.task36.service;

import davutcagri.task36.dto.NoteDTO;
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

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setLessonName(lesson.getLessonName());
        noteDTO.setStudentName(student.getFirstName() + " " + student.getLastName());
        noteDTO.setMidTermNote(note.getMidTermNote());
        noteDTO.setFinalNote(note.getFinalNote());
        noteDTO.setAverageNote(note.getAverageNote());
        return noteDTO;
    }

    public List<NoteDTO> findAll() {
        return noteRepository.findAll().stream().map(note -> {
            Student student = studentRepository.findById(note.getStudentId()).get();
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setLessonName(lessonRepository.findById(note.getLessonId()).get().getLessonName());
            noteDTO.setStudentName(student.getFirstName() + " " + student.getLastName());
            noteDTO.setMidTermNote(note.getMidTermNote());
            noteDTO.setFinalNote(note.getFinalNote());
            noteDTO.setAverageNote(note.getAverageNote());
            return noteDTO;
        }).collect(Collectors.toList());
    }

}
