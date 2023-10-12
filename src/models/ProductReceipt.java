package models;

public class ProductReceipt {
    private Long id;
    private Long id_product;
    private Long id_receip;

    @Override
    public String toString() {
        return "ProductReceipt{" +
                "id=" + id +
                ", id_product=" + id_product +
                ", id_receip=" + id_receip +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public Long getId_receip() {
        return id_receip;
    }

    public void setId_receip(Long id_receip) {
        this.id_receip = id_receip;
    }
}
