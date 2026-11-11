package com.escl.avapi.services;

import com.escl.avapi.entities.StockType;
import com.escl.avapi.repositories.StockTypeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class StockTypeService {

    private StockTypeRepository stockTypeRepository;

    public StockTypeService(StockTypeRepository stockTypeRepository) {
        this.stockTypeRepository = stockTypeRepository;
    }

    public List<StockType> findAll() {
        return stockTypeRepository.findAll();
    }

    public StockType save(StockType stockType) {
        return stockTypeRepository.save(stockType);
    }

    public StockType findById( Long id) {
        return stockTypeRepository.findById(id).get();
    }

    public StockType update(Long id, StockType stockType) {
        StockType existingStockType = stockTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "StockType not found with id: " + id
                ));
        existingStockType.setName(stockType.getName());
        return stockTypeRepository.save(existingStockType);
    }

    public void delete(Long id) {
        StockType existingStockType = stockTypeRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "StockType not found with id: " + id
                ));

        try {
            stockTypeRepository.delete(existingStockType);
        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "StockType with id: " + id + " cannot be delete."
            );
        }

    }
}
