package com.fullcycle.admin.catalogo.application.category.create;

public record CreateCategoryInput(
        String name,
        String description,
        boolean isActive
) {
    public static CreateCategoryInput with (
            final String name,
            final String description,
            final boolean isActive
    ) {
        return new CreateCategoryInput(name, description, isActive);
    }
}
