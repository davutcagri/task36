package davutcagri.task36.dto;

import lombok.Data;

@Data
public class NoteDTO {

    private String lessonName;
    private String studentName;
    private Double midTermNote;
    private Double finalNote;
    private Double averageNote;

}
