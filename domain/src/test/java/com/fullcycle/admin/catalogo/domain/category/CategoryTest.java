package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.exceptions.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCatagory_thenInstantiateACategory(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final Category actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;
        final Category actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorMessage ,actualException.getErrors().get(0).message());
        assertEquals(expectedErrorCount ,actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final var expectedName = " ";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;
        final Category actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorMessage ,actualException.getErrors().get(0).message());
        assertEquals(expectedErrorCount ,actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLenghtLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectedName = "Fi ";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final Category actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorMessage ,actualException.getErrors().get(0).message());
        assertEquals(expectedErrorCount ,actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNameLenghtMoreThen255_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final var expectedName = """
                O Fabuloso Gerador de Lero-lero v2.0 é capaz de gerar qualquer quantidade de texto vazio e prolixo, ideal para engrossar uma tese de mestrado, impressionar seu chefe ou preparar discursos capazes de curar a insônia da platéia. Basta informar
                 um título pomposo qualquer (nos moldes do que está sugerido aí embaixo) e a quantidade de frases desejada. Voilá! 
                 Em dois nano-segundos você terá um texto - ou mesmo um livro inteiro - pronto para impressão. Ou, se preferir, faça copy/paste para um editor de texto para formatá-lo mais sofisticadamente. Lembre-se: aparência é tudo, conteúdo é nada.
                """;
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;
        final Category actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException =
                assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorMessage ,actualException.getErrors().get(0).message());
        assertEquals(expectedErrorCount ,actualException.getErrors().size());
    }

    @Test
    public void givenAValidEmptyDescription_whenCallNewCatagory_thenInstantiateACategory(){
        final var expectedName = "Filmes";
        final var expectedDescription = "     ";
        final var expectedIsActive = true;
        final Category actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertDoesNotThrow(() -> {actualCategory.validate(new ThrowsValidationHandler());});
        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCatagory_thenInstantiateACategory(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;
        final Category actualCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertDoesNotThrow(() -> {actualCategory.validate(new ThrowsValidationHandler());});
        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final Category category =
                Category.newCategory("Film", "A catgoria", expectedIsActive);

        final var actualCategory =
                category.update(expectedName, expectedDescription, expectedIsActive);

        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());

    }

    @Test
    void givenAValidCategory_whenUpdateToInactive_thenReturnCategoryUpdated() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = false;
        final Category category =
                Category.newCategory("Film", "A catgoria", true);

        final var actualCategory =
                category.update(expectedName, expectedDescription, expectedIsActive);

        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertFalse(actualCategory.isActive());
    }
}
