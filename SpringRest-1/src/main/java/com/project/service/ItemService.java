package com.project.service;

import com.project.dto.ItemRequestDto;
import com.project.dto.ItemResponseDto;

public interface ItemService {

	public ItemResponseDto save(ItemRequestDto itemRequestDto);
	
}
