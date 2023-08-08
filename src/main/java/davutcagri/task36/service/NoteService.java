package davutcagri.task36.service;

import davutcagri.task36.dto.NoteDTO;
import davutcagri.task36.model.Note;
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

    public void save(Note note) {
        noteRepository.save(note);
    }

    public List<NoteDTO> findAll() {
        return noteRepository.findAll().stream().map(note -> {
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setMark(note.getMark());
            noteDTO.setStudentName(studentRepository.getStudentById(note.getStudentId()).getName());
            noteDTO.setLessonName(lessonRepository.getLessonById(note.getLessonId()).getName());
            return noteDTO;
        }).collect(Collectors.toList());
    }

}
