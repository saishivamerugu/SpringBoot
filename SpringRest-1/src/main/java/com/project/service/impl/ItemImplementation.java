package com.project.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.project.dao.ItemRepository;
import com.project.dto.ItemRequestDto;
import com.project.dto.ItemResponseDto;
import com.project.model.Item;
import com.project.service.ItemService;

@Service
public class ItemImplementation implements ItemService{

	private final ItemRepository itemRepository;
	
	public ItemImplementation(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
		
	}
	 
	@Override
	public ItemResponseDto save(ItemRequestDto itemRequestDto) {
		Item item = new Item();
		item.setPrice(itemRequestDto.getPrice());
		item.setDiscount(itemRequestDto.getDiscount());
		item.setItemName(itemRequestDto.getItemName());
		item.setAvailable(true);
		item.setStock(itemRequestDto.getStock());
		item.setRating(4.3); 
		
		Item savedItem  = itemRepository.save(item);
		
		// There is a class called BeanUtils have a method .copyProperties it will take a source object and target object .
		// savedItem is the source 2nd parameter  
		ItemResponseDto itemResponseDto = new ItemResponseDto();
		BeanUtils.copyProperties(savedItem, itemResponseDto);
		
		return itemResponseDto; 
	}

}
