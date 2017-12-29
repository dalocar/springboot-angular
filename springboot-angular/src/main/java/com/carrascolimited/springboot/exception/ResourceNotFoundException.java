package com.carrascolimited.springboot.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3350552373360029636L;

	private Long resourceId;

    public ResourceNotFoundException(Long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }
}