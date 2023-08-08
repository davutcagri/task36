package davutcagri.task36.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {

    private String name;
    private List<String> lessonNames;
    private List<Double> noteMarks;

}
