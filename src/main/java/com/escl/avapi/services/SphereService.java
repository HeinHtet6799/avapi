package com.escl.avapi.services;

import com.escl.avapi.entities.Sphere;
import com.escl.avapi.repositories.SphereRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class SphereService {

    private SphereRepository sphereRepository;

    public SphereService(SphereRepository sphereRepository) {
        this.sphereRepository = sphereRepository;
    }

    public List<Sphere> findAll() {
        return sphereRepository.findAll();
    }

    public Sphere findById(Long id) {
        return sphereRepository.findById(id).get();
    }

    public Sphere save(Sphere sphere) {
        return sphereRepository.save(sphere);
    }

    public Sphere update(Long id, Sphere sphere) {
        Sphere existing = sphereRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Sphere not found"
                ));
        existing.setValue(sphere.getValue());
        return sphereRepository.save(existing);
    }

    public void deleteSphere(Long id) {
        Sphere existing = sphereRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Sphere not found"
                ));
        try {
            sphereRepository.delete(existing);
        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Sphere already deleted");
        }
    }
}

