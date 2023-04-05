package  model;

public class Entity
{
    private int entity_id;
    private String entity_name;

    public Entity(final int entity_id, final String entity_name) {
        this.entity_id = entity_id;
        this.entity_name = entity_name;
    }

    public Entity(final String entity_name) {
        this.entity_name = entity_name;
    }

    public int getEntity_id() {
        return this.entity_id;
    }

    public void setEntity_id(final int entity_id) {
        this.entity_id = entity_id;
    }

    public String getEntity_name() {
        return this.entity_name;
    }

    public void setEntity_name(final String entity_name) {
        this.entity_name = entity_name;
    }
}

