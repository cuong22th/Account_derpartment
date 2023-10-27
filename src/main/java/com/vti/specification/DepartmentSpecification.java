package com.vti.specification;

import com.vti.entity.Department;
import com.vti.form.DepartmentFilterForm;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class DepartmentSpecification {
    public static Specification<Department> buildWhere (DepartmentFilterForm form){
        if (form==null){
            return  null;
        }
        return  hasCreatedDateEqual(form.getCreatedDate())
                .and(hasCreatedDateGreaterThanEqualTo(form.getMinCreatedDate()))
                .and(hasCreatedDateLessThanEqualTo(form.getMaxCreatedDate()))
                .and(hasCreatedYearGreaterThanEqualTo(form.getMinCreatedYear()))
                .and(hasTypeEqual(form.getType()));

    }
    private static Specification<Department> hasCreatedDateEqual(LocalDate createdDate){
        return new Specification<Department>() {
            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                 if (createdDate ==null){
                     return null;
                 }
                 return  builder.equal(root.get("createdDate").as(LocalDate.class), createdDate);
            }
        };
    }
    private static Specification<Department> hasCreatedDateGreaterThanEqualTo(LocalDate minCreatedDate){
        return new Specification<Department>() {
            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (minCreatedDate ==null){
                    return null;
                }
                return  builder.greaterThanOrEqualTo(root.get("createdDate").as(LocalDate.class), minCreatedDate);
            }
        };
    }
    private static Specification<Department> hasCreatedDateLessThanEqualTo(LocalDate maxCreatedDate){
        return new Specification<Department>() {
            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (maxCreatedDate ==null){
                    return null;
                }
                return  builder.lessThanOrEqualTo(root.get("createdDate").as(LocalDate.class), maxCreatedDate);
            }
        };
    }
    private static Specification<Department> hasCreatedYearGreaterThanEqualTo(Integer minCreatedYear){
        return new Specification<Department>() {
            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (minCreatedYear ==null){
                    return null;
                }
                return  builder.greaterThanOrEqualTo(builder.function("YEAR", Integer.class, root.get("createdDate")), minCreatedYear);
            }
        };
    }

    private static Specification<Department> hasTypeEqual(Department.Type type){
        return new Specification<Department>() {
            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                if (type ==null){
                    return null;
                }
                return  builder.equal(root.get("type"), type);
            }
        };
    }
}
