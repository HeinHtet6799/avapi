package com.escl.avapi.services;

import com.escl.avapi.entities.FrameType;
import com.escl.avapi.repositories.FrameTypeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class FrameTypeService {

    private FrameTypeRepository frameTypeRepository;

    public FrameTypeService(FrameTypeRepository frameTypeRepository) {
        this.frameTypeRepository = frameTypeRepository;
    }

    public List<FrameType> findAll() {
        return frameTypeRepository.findAll();
    }

    public FrameType findById(Long id) {
        return frameTypeRepository.findById(id).get();
    }

    public FrameType save(FrameType frameType) {
        return frameTypeRepository.save(frameType);
    }

    public FrameType update(Long id, FrameType frameType) {
        FrameType existing = frameTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "FrameType not found"
                ));
        existing.setName(frameType.getName());
        return frameTypeRepository.save(existing);
    }

    public void deleteFrameType(Long id) {
        FrameType existing = frameTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "FrameType not found"
                ));
        try {
            frameTypeRepository.delete(existing);
        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "FrameType already deleted");
        }
    }

}
