package davutcagri.task36.dto;

import davutcagri.task36.model.Note;
import davutcagri.task36.model.Student;
import lombok.Data;

import java.util.List;

@Data
public class LessonDTO {

    private String name;
    private String teacher;
    private List<String> studentNames;
    private List<NoteDTO> noteDTOs;

}
