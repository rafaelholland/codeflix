package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.pagination.Pagination;

import java.awt.print.Pageable;
import java.util.Optional;

public interface CategoryGateway {

    Category create(Category category);
    void deleteById(CategoryID id);
    Optional<Category> findById(CategoryID id);

    Category update(Category id);

    Pagination<Category> findAll(CategorySearchQuery query);




}
