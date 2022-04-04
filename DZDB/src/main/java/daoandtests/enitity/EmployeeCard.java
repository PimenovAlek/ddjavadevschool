package daoandtests.enitity;

import java.time.LocalDateTime;

public class EmployeeCard {
    private Long id;
    private String firstName;
    private String lastName;
    private String thirdName;
    private String login;
    private String password;
    private CompanyUnit companyUnit;
    private Roles roles;
    private UserPosition userPosition;
    private LocalDateTime creationDate;

    public EmployeeCard() {
    }

    public EmployeeCard(String firstName, String lastName, String thirdName, String login, String password,
                        CompanyUnit companyUnit, Roles roles, UserPosition userPosition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.thirdName = thirdName;
        this.login = login;
        this.password = password;
        this.companyUnit = companyUnit;
        this.roles = roles;
        this.userPosition = userPosition;
    }

    public EmployeeCard(Long id, String firstName, String lastName, String thirdName, String login, String password, CompanyUnit companyUnit, Roles roles, UserPosition userPosition, LocalDateTime creationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.thirdName = thirdName;
        this.login = login;
        this.password = password;
        this.companyUnit = companyUnit;
        this.roles = roles;
        this.userPosition = userPosition;
        this.creationDate = creationDate;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public UserPosition getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(UserPosition userPosition) {
        this.userPosition = userPosition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CompanyUnit getCompanyUnit() {
        return companyUnit;
    }

    public void setCompanyUnit(CompanyUnit companyUnit) {
        this.companyUnit = companyUnit;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "EmployeeCard{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", thirdName='" + thirdName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", companyUnit=" + companyUnit +
                ", roles=" + roles +
                ", userPosition=" + userPosition +
                ", creationDate=" + creationDate +
                '}';
    }
}
