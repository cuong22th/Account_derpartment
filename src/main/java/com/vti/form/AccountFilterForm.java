package com.vti.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountFilterForm {
    private String Search;
    private Integer minId;
    private Integer maxId;
}
