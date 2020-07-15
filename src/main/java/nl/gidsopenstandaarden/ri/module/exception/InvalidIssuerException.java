/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.exception;

/**
 *
 */
public class InvalidIssuerException extends RuntimeException {
	public InvalidIssuerException(String message) {
		super(message);
	}

	public InvalidIssuerException(Throwable cause) {
		super(cause);
	}
}
