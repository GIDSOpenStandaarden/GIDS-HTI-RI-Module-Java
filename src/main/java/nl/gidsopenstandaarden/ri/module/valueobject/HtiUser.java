/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.valueobject;

import java.io.Serializable;

/**
 *
 */
public class HtiUser implements Serializable {
	private String reference;
	private String name;
	private boolean anonymous = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}
}
