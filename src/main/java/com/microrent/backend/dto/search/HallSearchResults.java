package com.microrent.backend.dto.search;

import com.microrent.backend.dto.HallDto;

import java.util.List;

public class HallSearchResults {
    private Long count;
    private List<HallDto> list;

    public HallSearchResults(Long count, List<HallDto> list) {
        this.count = count;
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<HallDto> getList() {
        return list;
    }

    public void setList(List<HallDto> list) {
        this.list = list;
    }
}
