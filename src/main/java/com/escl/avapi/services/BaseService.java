package com.escl.avapi.services;

import com.escl.avapi.entities.Base;
import com.escl.avapi.repositories.BaseRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class BaseService {

    private BaseRepository baseRepository;

    public BaseService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    public List<Base> findAll() {
        return baseRepository.findAll();
    }

    public Base findById(Long id) {
        return baseRepository.findById(id).get();
    }

    public Base save(Base base) {
        return baseRepository.save(base);
    }

    public Base update(Long id, Base base) {
        Base existing = baseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Base not found"
                ));
        existing.setValue(base.getValue());
        return baseRepository.save(existing);
    }

    public void deleteBase(Long id) {
        Base existing = baseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Base not found"
                ));
        try {
            baseRepository.delete(existing);
        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Base already deleted");
        }
    }
}
