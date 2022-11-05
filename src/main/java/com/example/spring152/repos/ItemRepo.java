package com.example.spring152.repos;

import com.example.spring152.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<ItemModel, Long> {
    ItemModel findById(long id);

    /*@Query(value = "select*from shop_item where id = :id", nativeQuery = true)
    public ItemModel getById(long id);*/
}
