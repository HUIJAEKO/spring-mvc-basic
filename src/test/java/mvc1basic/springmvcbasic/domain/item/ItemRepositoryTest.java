package mvc1basic.springmvcbasic.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save(){
        Item item = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(item.getId());
        Assertions.assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll(){
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> items = itemRepository.findAll();

        Assertions.assertThat(items).size().isEqualTo(2);
        Assertions.assertThat(items).contains(item1, item2);
    }


    @Test
    void findByName(){
        Item item = new Item("item", 10000, 10);
        Long itemId = itemRepository.save(item).getId();

        Item newItem = new Item("newItem", 20000, 20);
        itemRepository.update(itemId, newItem);

        Item findItem = itemRepository.findById(itemId);

        Assertions.assertThat(findItem.getItemName()).isEqualTo(newItem.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(newItem.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(newItem.getQuantity());
    }
}