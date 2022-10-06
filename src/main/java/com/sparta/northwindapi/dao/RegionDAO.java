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

    public RegionDTO get(int id) {
        Optional<Region> optional = REPO.findById(id);
        Region region;
        if (optional.isPresent())
            region = optional.get();
        else
            return null;
        return new RegionDTO(region.getId(), region.getRegionDescription());
    }

    public List<RegionDTO> getAll() {
        List<Region> regions = REPO.findAll();
        if (regions.isEmpty())
            return null;
        return regions.stream().map(r -> new RegionDTO(r.getId(), r.getRegionDescription())).toList();
    }

    public RegionDTO create(RegionDTO region) {
        boolean exists = this.get(region.getId()) != null;
        if (exists)
            return null;
        Region toInsert = new Region();
        toInsert.setId(region.getId());
        toInsert.setRegionDescription(region.getRegionDescription());
        Region inserted = REPO.save(toInsert);
        return new RegionDTO(inserted.getId(), inserted.getRegionDescription());
    }

    public RegionDTO update(RegionDTO updated, int id) {
        boolean exists = this.get(id) != null;
        if (!exists)
            return null;
        Region toUpdate = new Region();
        toUpdate.setId(id);
        toUpdate.setRegionDescription(updated.getRegionDescription());
        Region result = REPO.save(toUpdate);
        return new RegionDTO(result.getId(), result.getRegionDescription());
    }

    public RegionDTO updateOrCreate(RegionDTO updated, int id) {
        Region toUpdate = new Region();
        toUpdate.setId(id);
        toUpdate.setRegionDescription(updated.getRegionDescription());
        Region result = REPO.save(toUpdate);
        return new RegionDTO(result.getId(), result.getRegionDescription());
    }
}
