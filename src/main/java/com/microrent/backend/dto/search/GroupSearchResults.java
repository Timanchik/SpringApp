package com.microrent.backend.dto.search;

import com.microrent.backend.dto.GroupDto;
import com.microrent.backend.dto.StyleDTO;

import java.util.List;

public class GroupSearchResults {
    private Long count;
    private List<GroupDto> list;

    public GroupSearchResults(Long count, List<GroupDto> list) {
        this.count = count;
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<GroupDto> getList() {
        return list;
    }

    public void setList(List<GroupDto> list) {
        this.list = list;
    }
}
