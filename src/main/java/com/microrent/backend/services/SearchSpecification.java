package com.microrent.backend.services;

import com.microrent.backend.models.searchingAndFiltering.FilterRequest;
import com.microrent.backend.models.searchingAndFiltering.SearchRequest;
import com.microrent.backend.models.searchingAndFiltering.SortRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchSpecification<T> implements Specification<T> {
    private final transient SearchRequest request;

    public SearchSpecification(SearchRequest request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

        for (FilterRequest filter : this.request.getFilters()) {
            predicate = filter.getOperator().build(root, cb, filter, predicate);
        }

        List<Order> orders = new ArrayList<>();
        for (SortRequest sort : this.request.getSorts()) {
            orders.add(sort.getDirection().build(root, cb, sort));
        }

        query.orderBy(orders);
        return predicate;
    }

    public static Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(Objects.requireNonNullElse(page, 0), Objects.requireNonNullElse(size, 100));
    }
}
