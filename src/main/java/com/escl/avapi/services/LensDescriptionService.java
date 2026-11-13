package com.escl.avapi.services;

import com.escl.avapi.entities.LensDescription;
import com.escl.avapi.repositories.LensDescriptionRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class LensDescriptionService {

    private LensDescriptionRepository lensDescriptionRepository;

    public LensDescriptionService(LensDescriptionRepository lensDescriptionRepository) {
        this.lensDescriptionRepository = lensDescriptionRepository;
    }

    public List<LensDescription> findAll() {
        return lensDescriptionRepository.findAll();
    }

    public LensDescription findById(Long id) {
        return lensDescriptionRepository.findById(id).get();
    }

    public LensDescription save(LensDescription lensDescription) {
        return lensDescriptionRepository.save(lensDescription);
    }

    public LensDescription update(Long id, LensDescription lensDescription) {
        LensDescription existing = lensDescriptionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "LensDescription not found"
                ));
        existing.setName(lensDescription.getName());
        return lensDescriptionRepository.save(existing);
    }

    public void deleteLensDescription(Long id) {
        LensDescription existing = lensDescriptionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "LensDescription not found"
                ));
        try {
            lensDescriptionRepository.delete(existing);
        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "LensDescription already deleted");
        }
    }

}
