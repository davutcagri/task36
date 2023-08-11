package davutcagri.task36.dto;

import lombok.Data;

@Data
public class NoteDTO {

    private LessonDTO lesson;
    private StudentDTO student;
    private Double midTermNote;
    private Double finalNote;
    private Double averageNote;

}
