package davutcagri.task36.dto;

import lombok.Data;

import java.util.List;

@Data
public class LessonDTO {

    private String lessonName;
    private List<NoteDTO> notes;

}
