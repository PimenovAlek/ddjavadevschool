package daoandtests.enitity;

import java.util.List;

public class Roles {
    private Long id;
    private String name;
    private List<Rights> rights;

    public Roles(Long id, String name, List<Rights> rights) {
        this.id = id;
        this.name = name;
        this.rights = rights;
    }

    public Roles() {
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

    public List<Rights> getRights() {
        return rights;
    }

    public void setRights(List<Rights> rights) {
        this.rights = rights;
    }
}
