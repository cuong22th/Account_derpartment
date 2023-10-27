package com.vti.form;

import com.vti.validation.DepartmentNameNotExit;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Getter
@Setter
public class DepartmentCreateForm {
    @NotBlank(message = "{Department.createDepartment.form.name.NotBlank}")
    @Length(max = 50,message = "{Department.createDepartment.form.name.Length}")
    @DepartmentNameNotExit(message = "{Department.createDepartment.form.name.NotExists}")
    private String name;

    @NotNull(message = "khong de trong")
    @PositiveOrZero(message = "nhap gia tri lon hon 0")
    private Integer totalMembers;

    @Pattern(
            regexp = "DEVELOPER|TESTER|SCRUM_MASTER|PROJECT_MANAGER",
            message = "department type must be DEVELOPER, TESTER, SCRUM_MASTER or PROJECT_MANAGER"
    )
    private String Type;
    private List<@Valid Account> accounts;
    @Getter
    @Setter
    public static class Account{
        @NotBlank(message = "Khong de trong")
        @Length(max = 50,message = "do dai nho hon 50")
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String role;
    }
}
