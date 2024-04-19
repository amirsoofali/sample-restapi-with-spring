package com.gerimedi.tasks.task.domain;

import lombok.*;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Task {

    private String code;
    private String source;
    private String codeListCode;
    private String displayValue;
    private String longDescription;
    private String fromDate;
    private String toDate;
    private Integer sortingPriority;


}
