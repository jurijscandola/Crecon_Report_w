package  model;

public class PCG_Garanzie
{
    private String id;
    private String pcg_garanzie;

    public PCG_Garanzie(final String id, final String pcg_garanzie) {
        this.id = id;
        this.pcg_garanzie = pcg_garanzie;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getPcg_garanzie() {
        return this.pcg_garanzie;
    }

    public void setPcg_garanzie(final String pcg_garanzie) {
        this.pcg_garanzie = pcg_garanzie;
    }
}
