package davutcagri.task36.dto;

import lombok.Data;

import java.util.List;

@Data
public class FailedAndSucceedStudentDTO {

    private SucceedStudentDTO succeedStudentDTO;
    private FailedStudentDTO failedStudentDTO;

    public FailedAndSucceedStudentDTO(SucceedStudentDTO succeedStudentDTOS, FailedStudentDTO failedStudentDTOS) {
        this.succeedStudentDTO = succeedStudentDTOS;
        this.failedStudentDTO = failedStudentDTOS;
    }

}
