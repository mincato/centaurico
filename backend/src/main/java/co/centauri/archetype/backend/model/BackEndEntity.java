package co.centauri.archetype.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class BackEndEntity extends BackEndObject {

    private static final long serialVersionUID = -5354058460706112830L;

    @JsonProperty("_id")
    private Long id;

    private EntityState state = EntityState.ACTIVE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
    }

}
