package com.microrent.backend.services;

import com.microrent.backend.dto.HallDto;
import com.microrent.backend.dto.StyleDTO;
import com.microrent.backend.dto.search.HallSearchResults;
import com.microrent.backend.models.Hall;
import com.microrent.backend.models.Style;
import com.microrent.backend.models.searchingAndFiltering.SearchRequest;
import com.microrent.backend.repositories.HallRepository;
import com.microrent.backend.util.DTOConverter;
import com.microrent.backend.util.exceptions.ObjectNotFoundException;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class HallService {
    private final HallRepository hallRepository;
    private final DTOConverter dtoConverter;

    @Autowired
    public HallService(HallRepository hallRepository, DTOConverter dtoConverter) {
        this.hallRepository = hallRepository;
        this.dtoConverter = dtoConverter;
    }

    @Transactional
    public void create(HallDto hallDto) {
        Hall hall = dtoConverter.convertToHall(hallDto);
        hallRepository.save(hall);
    }

    public HallSearchResults searchOperatingSystem(SearchRequest request){
        SearchSpecification<Hall> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Hall> halls = hallRepository.findAll(specification, pageable);
        return new HallSearchResults(halls.getTotalElements(), halls.stream().map(dtoConverter::convertToHallDto).collect(Collectors.toList()));
    }

    @Transactional
    public void delete(HallDto hallDto){
        Optional<Hall> hall = hallRepository.findById(hallDto.getId());
        if(hall.isPresent()){
            hallRepository.delete(hall.get());
        } else {
            throw new ObjectNotFoundException(String.format("Стиль с ID %d не найден!", hallDto.getId()));
        }
    }
}
