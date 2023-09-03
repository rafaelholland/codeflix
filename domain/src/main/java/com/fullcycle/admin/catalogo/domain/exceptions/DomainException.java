package com.fullcycle.admin.catalogo.domain.exceptions;

import com.fullcycle.admin.catalogo.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStacktraceException{

    private final List<Error> errors;

    private DomainException(
            final String aMessage,
            final List<Error> anErrors) {
        super(aMessage);
        this.errors = anErrors;
    }
    public static DomainException with(final List<Error> anErrors) {
        return new DomainException("", anErrors);
    }

    public static DomainException with(final Error aError) {
        return new DomainException("", List.of(aError));
    }

    public List<Error> getErrors() {
        return errors;
    }
}
