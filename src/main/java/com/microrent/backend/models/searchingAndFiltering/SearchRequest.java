package com.microrent.backend.models.searchingAndFiltering;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchRequest implements Serializable {
    private List<FilterRequest> filters;
    private List<SortRequest> sorts;
    private Integer page;
    private Integer size;

    public SearchRequest(List<FilterRequest> filters, List<SortRequest> sorts, Integer page, Integer size) {
        this.filters = filters;
        this.sorts = sorts;
        this.page = page;
        this.size = size;
    }

    public SearchRequest() {
    }

    public void setFilters(List<FilterRequest> filters) {
        this.filters = filters;
    }

    public void setSorts(List<SortRequest> sorts) {
        this.sorts = sorts;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<FilterRequest> getFilters() {
        if (Objects.isNull(this.filters)) return new ArrayList<>();
        return this.filters;
    }

    public List<SortRequest> getSorts() {
        if (Objects.isNull(this.sorts)) return new ArrayList<>();
        return this.sorts;
    }
}
