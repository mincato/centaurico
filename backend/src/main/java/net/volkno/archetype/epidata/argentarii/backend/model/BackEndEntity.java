package net.volkno.archetype.epidata.argentarii.backend.model;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.Id;

public abstract class BackEndEntity extends BackEndObject {

    private static final long serialVersionUID = -5354058460706112830L;

    @Id
    @JsonProperty("_id")
    private String id;

    private EntityState state = EntityState.ACTIVE;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
    }

}
