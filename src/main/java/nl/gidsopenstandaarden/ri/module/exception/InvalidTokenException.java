/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.exception;

/**
 *
 */
public class InvalidTokenException extends RuntimeException {
	public InvalidTokenException(String message) {
		super(message);
	}

	public InvalidTokenException(Throwable cause) {
		super(cause);
	}
}
