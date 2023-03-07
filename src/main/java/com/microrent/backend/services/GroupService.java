package com.microrent.backend.services;

import com.microrent.backend.controllers.GroupController;
import com.microrent.backend.dto.GroupDto;
import com.microrent.backend.dto.search.GroupSearchResults;
import com.microrent.backend.dto.search.StyleSearchResults;
import com.microrent.backend.models.Group;
import com.microrent.backend.models.Style;
import com.microrent.backend.models.searchingAndFiltering.SearchRequest;
import com.microrent.backend.repositories.GroupRepository;
import com.microrent.backend.util.DTOConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class GroupService {

    private final GroupRepository groupRepository;

    private final DTOConverter dtoConverter;

    public GroupService(GroupRepository groupRepository, DTOConverter dtoConverter) {
        this.groupRepository = groupRepository;
        this.dtoConverter = dtoConverter;
    }

    @Transactional
    public void create(GroupDto groupDto){
        Group group = dtoConverter.ConvertToGroup(groupDto);
        groupRepository.save(group);
    }

    public GroupSearchResults searchOperatingSystem(SearchRequest request){
        SearchSpecification<Group> specification = new SearchSpecification<>(request);
        Pageable pageable = SearchSpecification.getPageable(request.getPage(), request.getSize());
        Page<Group> groups = groupRepository.findAll(specification, pageable);
        return new GroupSearchResults(groups.getTotalElements(), groups.stream().map(dtoConverter::convertToGroupDto).collect(Collectors.toList()));
    }
}
