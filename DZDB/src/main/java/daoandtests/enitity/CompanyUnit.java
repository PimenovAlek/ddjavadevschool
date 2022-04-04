package daoandtests.enitity;

import java.time.LocalDateTime;
import java.util.List;

public class CompanyUnit {
    private Long id;
    private String name;
    private Long headquarterId;
    private List<EmployeeCard> employees;
    private LocalDateTime creationDate;

    public CompanyUnit( String name, Long headquarterId) {
        this.name = name;
        this.headquarterId = headquarterId;

    }

    public CompanyUnit(Long id, String name, Long headquarterId, List<EmployeeCard> employees, LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.headquarterId = headquarterId;
        this.employees = employees;
        this.creationDate = creationDate;
    }

    public CompanyUnit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHeadquarterId() {
        return headquarterId;
    }

    public void setHeadquarterId(Long headquarter) {
        this.headquarterId = headquarter;
    }

    public List<EmployeeCard> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeCard> employees) {
        this.employees = employees;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
