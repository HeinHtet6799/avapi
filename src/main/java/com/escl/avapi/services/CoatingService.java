package com.escl.avapi.services;

import com.escl.avapi.entities.Coating;
import com.escl.avapi.repositories.CoatingRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CoatingService {

    private CoatingRepository coatingRepository;

    public CoatingService(CoatingRepository coatingRepository) {
        this.coatingRepository = coatingRepository;
    }

    public List<Coating> findAll() {
        return coatingRepository.findAll();
    }

    public Coating findById(Long id) {
        return coatingRepository.findById(id).get();
    }

    public Coating save(Coating coating) {
        return coatingRepository.save(coating);
    }

    public Coating update(Long id, Coating coating) {
        Coating existing = coatingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Coating not found"
                ));
        existing.setName(coating.getName());
        return coatingRepository.save(existing);
    }

    public void deleteCoating(Long id) {
        Coating existing = coatingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Coating not found"
                ));
        try {
            coatingRepository.delete(existing);
        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Coating already deleted");
        }
    }
}
