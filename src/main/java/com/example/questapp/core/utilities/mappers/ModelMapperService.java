package com.example.questapp.core.utilities.mappers;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	
	ModelMapper forResponse();
	ModelMapper forRequest();
	
	
}