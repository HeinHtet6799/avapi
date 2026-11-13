package com.escl.avapi.services;

import com.escl.avapi.entities.Item;
import com.escl.avapi.repositories.ItemRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ItemService {
    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).get();
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Item update(Long id, Item item) {
        Item existing = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item not found"
                ));
        existing.setName(item.getName());
        return itemRepository.save(existing);
    }

    public void deleteItem(Long id) {
        Item existing = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Item not found"
                ));
        try {
            itemRepository.delete(existing);
        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Item already deleted");
        }
    }
}
