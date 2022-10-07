package com.sparta.northwindapi.dto;

import com.sparta.northwindapi.entity.Territory;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Territory} entity
 */
public class TerritoryDto implements Serializable {
    private final String id;
    private final String territoryDescription;

    public TerritoryDto(String id, String territoryDescription) {
        this.id = id;
        this.territoryDescription = territoryDescription;
    }

    public String getId() {
        return id;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TerritoryDto entity = (TerritoryDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.territoryDescription, entity.territoryDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, territoryDescription);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "territoryDescription = " + territoryDescription + ")";
    }
}