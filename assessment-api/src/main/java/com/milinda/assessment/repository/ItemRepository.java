package com.milinda.assessment.repository;

import com.milinda.assessment.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
