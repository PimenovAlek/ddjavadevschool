package dz.servlet.dto;

public class UpdateUserPositionRequest {
    private Long id;
    private String name;

    public UpdateUserPositionRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UpdateUserPositionRequest() {
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
}
