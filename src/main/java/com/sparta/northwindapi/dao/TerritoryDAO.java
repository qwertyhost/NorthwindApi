package com.sparta.northwindapi.dao;

import com.sparta.northwindapi.dto.TerritoryDto;
import com.sparta.northwindapi.entity.Territory;
import com.sparta.northwindapi.repo.TerritoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TerritoryDAO {
    private final TerritoryRepository REPO;

    public TerritoryDAO(TerritoryRepository repo) { this.REPO = repo; }

    public TerritoryDto getTerritoryById(String id) {
        Optional<Territory> optional = REPO.findById(id);
        Territory territory;
        if (optional.isPresent())
            territory = optional.get();
        else
            return null;
        return new TerritoryDto(territory.getId(), territory.getTerritoryDescription());
    }

    public List<TerritoryDto> getAllTerritories() {
        List<Territory> territories = REPO.findAll();
        if (territories.isEmpty())
            return null;
        return territories.stream().map(r -> new TerritoryDto(r.getId(), r.getTerritoryDescription())).toList();
    }
}
