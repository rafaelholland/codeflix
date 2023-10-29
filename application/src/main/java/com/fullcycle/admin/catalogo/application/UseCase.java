package com.fullcycle.admin.catalogo.application;


import com.fullcycle.admin.catalogo.domain.category.Category;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN in);

    public Category execute() {
        return null;
    }

}