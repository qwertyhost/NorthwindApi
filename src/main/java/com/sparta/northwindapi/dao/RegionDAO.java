package com.sparta.northwindapi.dao;

import com.sparta.northwindapi.dto.RegionDTO;
import com.sparta.northwindapi.entity.Region;
import com.sparta.northwindapi.repo.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionDAO {

    private final RegionRepository REPO;

    public RegionDAO(RegionRepository repo) { this.REPO = repo; }

    public RegionDTO getRegionById(int id) {
        Optional<Region> optional = REPO.findById(id);
        Region region;
        if (optional.isPresent())
            region = optional.get();
        else
            return null;
        return new RegionDTO(region.getId(), region.getRegionDescription());
    }

    public List<RegionDTO> getAllRegions() {
        List<Region> regions = REPO.findAll();
        if (regions.isEmpty())
            return null;
        return regions.stream().map(r -> new RegionDTO(r.getId(), r.getRegionDescription())).toList();
    }
}
