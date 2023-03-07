package com.microrent.backend.services;

import com.microrent.backend.dto.StyleDTO;
import com.microrent.backend.dto.search.StyleSearchResults;
import com.microrent.backend.models.Style;
import com.microrent.backend.models.User;
import com.microrent.backend.models.searchingAndFiltering.SearchRequest;
import com.microrent.backend.repositories.StyleRepository;
import com.microrent.backend.util.DTOConverter;
import com.microrent.backend.util.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class StyleService {

    private final StyleRepository styleRepository;

    private final DTOConverter dtoConverter;

    public StyleService(StyleRepository styleRepository, DTOConverter dtoConverter) {
        this.styleRepository = styleRepository;
        this.dtoConverter = dtoConverter;
    }

    @Transactional
    public void save(StyleDTO styleDTO){
        Style style = dtoConverter.convertToStyle(styleDTO);
        //todo style validation
        styleRepository.save(style);
    }

    public StyleSearchResults searchOperatingSystem(SearchRequest request){
        SearchSpecification<Style> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Style> styles = styleRepository.findAll(specification, pageable);
        return new StyleSearchResults(styles.getTotalElements(), styles.stream().map(dtoConverter::convertToStyleDto).collect(Collectors.toList()));
    }

    @Transactional
    public void delete(StyleDTO styleDTO){
        Optional<Style> style = styleRepository.findById(styleDTO.getId());
        if(style.isPresent()){
            styleRepository.delete(style.get());
        } else {
            throw new ObjectNotFoundException(String.format("Стиль с ID %d не найден!", styleDTO.getId()));
        }
    }
}
