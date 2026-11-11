package com.escl.avapi.services;

import com.escl.avapi.entities.Brand;
import com.escl.avapi.repositories.BrandRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public Brand findById(Long id) {
        return brandRepository.findById(id).get();
    }

    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand update(Long id, Brand brand) {
        Brand existingBrand = brandRepository.findById(id)
                        .orElseThrow(() -> new  ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Brand not found!"
                        ));


        existingBrand.setName(brand.getName());
        existingBrand.setType(brand.getType());
        return brandRepository.save(existingBrand);
    }

    public void delete(Long id) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new  ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Brand not found!"
                ));
        try {
            brandRepository.delete(existingBrand);
        }catch (DataIntegrityViolationException e) {
            throw new  ResponseStatusException(HttpStatus.CONFLICT, "Brand cannot delete!");
        }
    }
}
