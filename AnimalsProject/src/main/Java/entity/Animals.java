package entity;

import java.io.Serializable;
import java.util.List;

public class Animals implements Serializable {
    private long id;
    private String name;
    private String group;
    private String environment;
    private List<Organs> organsList;

    public Animals() {
    }

    public Animals(long id, String name, String group, String environment, List<Organs> organsList) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.environment = environment;
        this.organsList = organsList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public List<Organs> getOrgansList() {
        return organsList;
    }

    public void setOrgansList(List<Organs> organsList) {
        this.organsList = organsList;
    }

    @Override
    public String toString() {
        return "Animals{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", environment='" + environment + '\'' +
                ", organsList=" + organsList +
                '}';
    }
}
