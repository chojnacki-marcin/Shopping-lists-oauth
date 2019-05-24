package com.chojnacki.shoppinglist.repository;

import com.chojnacki.shoppinglist.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chojnacki.shoppinglist.model.User;
import java.util.List;


@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
    List<ShoppingList> findAllByOwner(User user);
}
