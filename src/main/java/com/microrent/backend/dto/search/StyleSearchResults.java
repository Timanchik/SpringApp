package com.microrent.backend.dto.search;

import com.microrent.backend.dto.StyleDTO;
import com.microrent.backend.dto.UserDTO;

import java.util.List;

public class StyleSearchResults {
    private Long count;
    private List<StyleDTO> list;

    public StyleSearchResults(Long count, List<StyleDTO> list) {
        this.count = count;
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<StyleDTO> getList() {
        return list;
    }

    public void setList(List<StyleDTO> list) {
        this.list = list;
    }
}
