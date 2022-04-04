package dz.servlet.dto;

public class CreateCompanyUnitRequest {
    private Long id;
    private String name;
    private Long headquarterId;

    public CreateCompanyUnitRequest(String name, Long headquarterId) {
        this.name = name;
        this.headquarterId = headquarterId;
    }

    public CreateCompanyUnitRequest() {
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

    public void setHeadquarterId(Long headquarterId) {
        this.headquarterId = headquarterId;
    }
}
