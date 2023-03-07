package com.microrent.backend.dto.search;

import com.microrent.backend.dto.UserDTO;

import java.util.List;

public class UserSearchResults {
    private Long count;
    private List<UserDTO> list;

    public UserSearchResults(Long count, List<UserDTO> list) {
        this.count = count;
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<UserDTO> getList() {
        return list;
    }

    public void setList(List<UserDTO> list) {
        this.list = list;
    }
}
