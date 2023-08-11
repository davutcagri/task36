package davutcagri.task36.dto;

import lombok.Data;

import java.util.List;

@Data
public class LessonDTO {

    private String lessonName;
    private Double averageMark;
    private List<NoteDTO> notes;

}
