package daoandtests.enitity;

public class Rights {
    private Long id;
    private String name;

    public Rights(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Rights(String name) {
        this.name = name;
    }

    public Rights() {
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
