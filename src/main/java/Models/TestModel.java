package Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@AllArgsConstructor
@EqualsAndHashCode
public class TestModel {
    private String duration;
    private String method;
    private String name;
    private String startTime;
    private String endTime;
    private String status;
}

