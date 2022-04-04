package daoandtests.enitity;

public class UserPosition {
    private Long id;
    private String name;

    public UserPosition(String name) {
        this.name = name;
    }

    public UserPosition(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserPosition() {
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
