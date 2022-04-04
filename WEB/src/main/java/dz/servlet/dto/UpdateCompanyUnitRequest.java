package dz.servlet.dto;

public class UpdateCompanyUnitRequest {
    private Long id;
    private String name;
    private Long headquarterId;

    public UpdateCompanyUnitRequest() {
    }

    public UpdateCompanyUnitRequest(String name, Long headquarter) {
        this.name = name;
        this.headquarterId = headquarter;
    }

    public UpdateCompanyUnitRequest(Long id, String name, Long headquarter) {
        this.id = id;
        this.name = name;
        this.headquarterId = headquarter;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
