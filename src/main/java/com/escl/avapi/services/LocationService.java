package com.escl.avapi.services;

import com.escl.avapi.entities.Location;
import com.escl.avapi.repositories.LocationRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public Location findById(Long id) {
        return locationRepository.findById(id).get();
    }

    public Location update(Long id, Location location) {
        Location existing = locationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Location with "+ id +"not found"
                ));

        existing.setName(location.getName());
        existing.setAddress(location.getAddress());
        existing.setAlias(location.getAlias());
        existing.setTel(location.getTel());
        existing.setTopUp(location.getTopUp());
        existing.setAutoTransfer(location.isAutoTransfer());
        existing.setActive(location.isActive());
        return locationRepository.save(existing);
    }

    public void  delete(Long id) {
        Location existing = locationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Location with "+ id +"not found."
                ));
        try {
            locationRepository.delete(existing);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Cannot Delete Location "+ id +"because it is used elsewhere.");
        }
    }
}
