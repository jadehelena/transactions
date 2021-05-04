package com.jadehelena.transactions.exception.validation.dto

class ValidationErrorDto {
    private String field
    private String errorMessage

    ValidationErrorDto(String field, String errorMessage){
        this.field = field
        this.errorMessage = errorMessage
    }

    String getField() {
        return field
    }

    String getErrorMessage() {
        return errorMessage
    }
}
