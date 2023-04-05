package  model;

public class Affidamento
{
    private String id;
    private int entity_Id;
    private Double affidamento;

    public Affidamento(final String id, final int entity_Id, final Double value) {
        this.id = id;
        this.entity_Id = entity_Id;
        this.affidamento = value;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public int getEntity_Id() {
        return this.entity_Id;
    }

    public void setEntity_Id(final int entity_Id) {
        this.entity_Id = entity_Id;
    }

    public Double getAffidamento() {
        return this.affidamento;
    }

    public void setAffidamento(final Double affidamento) {
        this.affidamento = affidamento;
    }
}
