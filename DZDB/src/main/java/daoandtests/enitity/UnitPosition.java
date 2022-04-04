package daoandtests.enitity;


public class UnitPosition {
    private Long id;
    private Long parent_id;


    public UnitPosition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public String toString() {
        return "UnitPosition{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                '}';
    }
}
