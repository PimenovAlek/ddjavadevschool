package dz.servlet.dto;

public class CreateUserPositionRequest {
    private Long id;
    private String name;

    public CreateUserPositionRequest( String name) {

        this.name = name;
    }

    public CreateUserPositionRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
