package vn.hoidanit.jobhunter.domain.response;

import java.time.Instant;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.hoidanit.jobhunter.util.constant.LevelEnum;


@Getter
@Setter
public class ResUpdateJobDTO {
    private Long id;

    private String name;
    private String location;
    private double salary;
    private int quantity;
    private LevelEnum level;
    private Instant startDate;
    private Instant endDate;
    private boolean isActive;
    private Instant updatedAt;
    private String updatedBy;

    private List<String> skills;
}
