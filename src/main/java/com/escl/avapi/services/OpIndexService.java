package com.escl.avapi.services;

import com.escl.avapi.entities.OpIndex;
import com.escl.avapi.repositories.OpIndexRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class OpIndexService {

    private OpIndexRepository opIndexRepository;

    public OpIndexService(OpIndexRepository opIndexRepository) {
        this.opIndexRepository = opIndexRepository;
    }

    public List<OpIndex> findAll() {
        return opIndexRepository.findAll();
    }

    public OpIndex findById(Long id) {
        return opIndexRepository.findById(id).get();
    }

    public OpIndex save(OpIndex opIndex) {
        return opIndexRepository.save(opIndex);
    }

    public OpIndex update(Long id, OpIndex opIndex) {
        OpIndex existing = opIndexRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "OpIndex not found"
                ));
        existing.setName(opIndex.getName());
        return opIndexRepository.save(existing);
    }

    public void deleteOpIndex(Long id) {
        OpIndex existing = opIndexRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "OpIndex not found"
                ));
        try {
            opIndexRepository.delete(existing);
        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "OpIndex already deleted");
        }
    }
    
}
