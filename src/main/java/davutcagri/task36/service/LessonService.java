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

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName(student.getFirstName());
            studentDTO.setLastName(student.getLastName());
            studentDTO.setEmail(student.getEmail());

            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setStudent(studentDTO);
            noteDTO.setMidTermNote(note.getMidTermNote());
            noteDTO.setFinalNote(note.getFinalNote());
            noteDTO.setAverageNote(note.getAverageNote());
            return noteDTO;
        }).collect(Collectors.toList()));
        return lessonDTO;
    }

    public List<LessonDTO> findAll() {
        return lessonRepository.findAll().stream().map(lesson -> {
            calculateAverageNote(lesson);
            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setLessonName(lesson.getLessonName());
            lessonDTO.setAverageMark(lesson.getAverateNote());
            lessonDTO.setNotes(noteRepository.findAllById(lesson.getNoteIds()).stream().map(note -> {
                Student student = studentRepository.findById(note.getStudentId()).get();

                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setFirstName(student.getFirstName());
                studentDTO.setLastName(student.getLastName());
                studentDTO.setEmail(student.getEmail());

                NoteDTO noteDTO = new NoteDTO();
                noteDTO.setStudent(studentDTO);
                noteDTO.setMidTermNote(note.getMidTermNote());
                noteDTO.setFinalNote(note.getFinalNote());
                noteDTO.setAverageNote(note.getAverageNote());
                return noteDTO;
            }).collect(Collectors.toList()));
            return lessonDTO;
        }).collect(Collectors.toList());
    }

    public LessonDTO getOne(String lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        List<Note> notes = noteRepository.findNotesByLessonId(lessonId);

        calculateAverageNote(lesson);

        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setLessonName(lesson.getLessonName());
        lessonDTO.setAverageMark(lesson.getAverateNote());
        lessonDTO.setNotes(notes.stream().map(note -> {
            Student student = studentRepository.findById(note.getStudentId()).get();

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName(student.getFirstName());
            studentDTO.setLastName(student.getLastName());
            studentDTO.setEmail(student.getEmail());

            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setStudent(studentDTO);
            noteDTO.setMidTermNote(note.getMidTermNote());
            noteDTO.setFinalNote(note.getFinalNote());
            noteDTO.setAverageNote(note.getAverageNote());
            return noteDTO;
        }).collect(Collectors.toList()));
        return lessonDTO;
    }

    public void calculateAverageNote(Lesson lesson) {
        List<Note> notes = noteRepository.findNotesByLessonId(lesson.getLessonId());
        double totalNote = 0;
        for (Note note : notes) {
            totalNote = +note.getAverageNote();
        }
        lesson.setAverateNote(totalNote / notes.size());
        lessonRepository.save(lesson);
    }

}
