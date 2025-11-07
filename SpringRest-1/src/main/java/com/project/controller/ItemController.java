package com.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ItemRequestDto;
import com.project.dto.ItemResponseDto;
import com.project.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	private final ItemService itemService;
	
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}


	@PostMapping("/save")
	public ItemResponseDto saveData(@RequestBody ItemRequestDto itemRequestDto) {
		ItemResponseDto save = itemService.save(itemRequestDto);
		return save;
	}
	
	
	
}
