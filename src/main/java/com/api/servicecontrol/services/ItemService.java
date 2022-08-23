package com.api.servicecontrol.services;

import com.api.servicecontrol.models.ItemModel;
import com.api.servicecontrol.models.OrderModel;
import com.api.servicecontrol.repositories.ItemRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {

    final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public ItemModel save(ItemModel itemModel) {
        return itemRepository.save(itemModel);
    }

    public Optional<ItemModel> findById(UUID id) {
        return itemRepository.findById(id);
    }

    @Transactional
    public void delete(ItemModel itemModel) {
        itemRepository.delete(itemModel);
    }


    public List<ItemModel> findAll() {
        return itemRepository.findAll();
    }
}
