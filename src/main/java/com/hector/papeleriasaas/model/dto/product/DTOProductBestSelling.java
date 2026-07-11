package com.hector.papeleriasaas.model.dto.product;

import java.util.Objects;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOProductBestSelling {

    private Integer id;
    private String description;
    private Integer quantitySold;

    public DTOProductBestSelling() {
    }

    public DTOProductBestSelling(Builder builder) {
        this.id = builder.id;
        this.description = builder.description;
        this.quantitySold = builder.quantitySold;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantitySold() {
        return quantitySold;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        DTOProductBestSelling that = (DTOProductBestSelling) object;
        return id == that.id && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "DTOProductBestSelling{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", timesSold=" + quantitySold +
                '}';
    }

    public static class Builder {

        private Integer id;
        private String description;
        private Integer quantitySold;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder quantitySold(Integer timesSold) {
            this.quantitySold = timesSold;
            return this;
        }

        public DTOProductBestSelling build() {
            return new DTOProductBestSelling(this);
        }

    }
}
