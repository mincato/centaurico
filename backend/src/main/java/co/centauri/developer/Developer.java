package co.centauri.developer;

import co.centauri.BackEndEntity;

public class Developer extends BackEndEntity {

    private static final long serialVersionUID = 6686724366732219305L;

    private String name;
    private String lastName;
    private Seniority seniority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Seniority getSeniority() {
        return seniority;
    }

    public void setSeniority(Seniority seniority) {
        this.seniority = seniority;
    }

}
