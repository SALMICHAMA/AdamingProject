package entity;

import java.io.Serializable;

public class Organs implements Serializable {

    private Long id;
    private String name;
    private String description;
    private boolean isVital;

    public Organs() {
    }

    public Organs(Long id, String name, String description, boolean isVital) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isVital = isVital;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVital() {
        return isVital;
    }

    public void setVital(boolean vital) {
        isVital = vital;
    }

    @Override
    public String toString() {
        return "Organs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isVital=" + isVital +
                '}';
    }
}
