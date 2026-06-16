package com.hector.papeleriasaas.model.dto.product;

import java.util.Objects;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class DTOProductBestSelling {

    private int id;
    private String description;
    private int timesSold;

    public DTOProductBestSelling() {
    }

    public DTOProductBestSelling(Builder builder) {
        id = builder.id;
        description = builder.description;
        timesSold = builder.timesSold;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getTimesSold() {
        return timesSold;
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
                ", timesSold=" + timesSold +
                '}';
    }

    public static class Builder {

        private int id;
        private String description;
        private int timesSold;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder timesSold(int timesSold) {
            this.timesSold = timesSold;
            return this;
        }

        public DTOProductBestSelling build() {
            return new DTOProductBestSelling(this);
        }

    }
}
