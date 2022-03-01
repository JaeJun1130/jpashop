package com.jpabook.jpashop.service;

import com.jpabook.jpashop.Controller.BookForm;
import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.domain.item.Item;
import com.jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void save(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItem() {
        return itemRepository.findAll();
    }

    @Transactional
    public void updateItem(BookForm bookForm){
        Book book = (Book) itemRepository.findOne(bookForm.getId());

        //마찬가지로 set 을 사용해 업데이트를 하는것 보단 Entity 비즈니스 로직을 통해 업데이트 하는것이 좀더 유지보수성이 좋음
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setIsbn(bookForm.getIsbn());
        book.setAuthor(bookForm.getAuthor());
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);

    }
}
