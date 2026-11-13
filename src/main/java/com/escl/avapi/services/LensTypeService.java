package com.escl.avapi.services;

import com.escl.avapi.entities.LensType;
import com.escl.avapi.repositories.LensTypeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class LensTypeService {
    
    private LensTypeRepository lensTypeRepository;

    public LensTypeService(LensTypeRepository lensTypeRepository) {
        this.lensTypeRepository = lensTypeRepository;
    }

    public List<LensType> findAll() {
        return lensTypeRepository.findAll();
    }

    public LensType findById(Long id) {
        return lensTypeRepository.findById(id).get();
    }

    public LensType save(LensType lensType) {
        return lensTypeRepository.save(lensType);
    }

    public LensType update(Long id, LensType lensType) {
        LensType existing = lensTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "LensType not found"
                ));
        existing.setName(lensType.getName());
        return lensTypeRepository.save(existing);
    }

    public void deleteLensType(Long id) {
        LensType existing = lensTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "LensType not found"
                ));
        try {
            lensTypeRepository.delete(existing);
        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "LensType already deleted");
        }
    }

}
