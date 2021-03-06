package webclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webclient.model.ShoppingList;
import webclient.model.User;
import webclient.repository.ShoppingListRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JpaShoppingListService implements ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public JpaShoppingListService(ShoppingListRepository shoppingListRepository, UserService userService) {
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public void saveShoppingList(ShoppingList shoppingList, User user){
        shoppingList.setOwner(user);
        shoppingListRepository.save(shoppingList);
    }

    @Override
    public Optional<ShoppingList> findById(long id){
        return shoppingListRepository.findById(id);
    }


    @Override
    public boolean isOwner(String userIdentifier, long shoppingListId){
        Optional<ShoppingList> result = shoppingListRepository.findById(shoppingListId);
        return result.filter(shoppingList -> shoppingList.getOwner().getUserIdentifier().equals(userIdentifier))
                .isPresent();
    }

    @Override
    public List<ShoppingList> findAllByAccount(User user) {
        return shoppingListRepository.findAllByOwner(user);
    }
}
