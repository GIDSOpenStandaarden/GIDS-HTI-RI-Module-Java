/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 */
@Entity
public class JwtId {
	@Id
	String id;
}
