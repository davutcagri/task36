package davutcagri.task36.service;

import davutcagri.task36.dto.LessonDTO;
import davutcagri.task36.dto.NoteDTO;
import davutcagri.task36.model.Lesson;
import davutcagri.task36.model.Note;
import davutcagri.task36.model.Student;
import davutcagri.task36.repository.LessonRepository;
import davutcagri.task36.repository.NoteRepository;
import davutcagri.task36.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final NoteRepository noteRepository;
    private final StudentRepository studentRepository;

    public LessonService(LessonRepository lessonRepository, NoteRepository noteRepository, StudentRepository studentRepository) {
        this.lessonRepository = lessonRepository;
        this.noteRepository = noteRepository;
        this.studentRepository = studentRepository;
    }

    public LessonDTO save(Lesson lesson) {
        lessonRepository.save(lesson);

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLessonName(lesson.getLessonName());
        lessonDTO.setNotes(noteRepository.findAllById(lesson.getNoteIds()).stream().map(note -> {
            Student student = studentRepository.findById(note.getStudentId()).get();
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setLessonName(lesson.getLessonName());
            noteDTO.setStudentName(student.getFirstName() + " " + student.getLastName());
            noteDTO.setMidTermNote(note.getMidTermNote());
            noteDTO.setFinalNote(note.getFinalNote());
            noteDTO.setAverageNote(note.getAverageNote());
            return noteDTO;
        }).collect(Collectors.toList()));
        return lessonDTO;
    }

    public List<LessonDTO> findAll() {
        return lessonRepository.findAll().stream().map(lesson -> {
            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setLessonName(lesson.getLessonName());
            lessonDTO.setNotes(noteRepository.findAllById(lesson.getNoteIds()).stream().map(note -> {
                Student student = studentRepository.findById(note.getStudentId()).get();
                NoteDTO noteDTO = new NoteDTO();
                noteDTO.setLessonName(lessonRepository.findById(note.getLessonId()).get().getLessonName());
                noteDTO.setStudentName(student.getFirstName() + " " + student.getLastName());
                noteDTO.setMidTermNote(note.getMidTermNote());
                noteDTO.setFinalNote(note.getFinalNote());
                noteDTO.setAverageNote(note.getAverageNote());
                return noteDTO;
            }).collect(Collectors.toList()));
            return lessonDTO;
        }).collect(Collectors.toList());
    }

    public LessonDTO getOne(String lessonId) {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        List<Note> notes = noteRepository.findNotesByLessonId(lessonId);

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLessonName(lesson.get().getLessonName());
        lessonDTO.setNotes(notes.stream().map(note -> {
            Student student = studentRepository.findById(note.getStudentId()).get();
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setLessonName(lessonRepository.findById(note.getLessonId()).get().getLessonName());
            noteDTO.setStudentName(student.getFirstName() + " " + student.getLastName());
            noteDTO.setMidTermNote(note.getMidTermNote());
            noteDTO.setFinalNote(note.getFinalNote());
            noteDTO.setAverageNote(note.getAverageNote());
            return noteDTO;
        }).collect(Collectors.toList()));
        return lessonDTO;
    }

}
