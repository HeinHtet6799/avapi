package com.escl.avapi.services;

import com.escl.avapi.entities.Cylinder;
import com.escl.avapi.repositories.CylinderRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CylinderService {

    private CylinderRepository cylinderRepository;

    public CylinderService(CylinderRepository cylinderRepository) {
        this.cylinderRepository = cylinderRepository;
    }

    public List<Cylinder> findAll() {
        return cylinderRepository.findAll();
    }

    public Cylinder findById(Long id) {
        return cylinderRepository.findById(id).get();
    }

    public Cylinder save(Cylinder cylinder) {
        return cylinderRepository.save(cylinder);
    }

    public Cylinder update(Long id, Cylinder cylinder) {
        Cylinder existing = cylinderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cylinder not found"
                ));
        existing.setValue(cylinder.getValue());
        return cylinderRepository.save(existing);
    }

    public void deleteCylinder(Long id) {
        Cylinder existing = cylinderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cylinder not found"
                ));
        try {
            cylinderRepository.delete(existing);
        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Cylinder already deleted");
        }
    }
}
