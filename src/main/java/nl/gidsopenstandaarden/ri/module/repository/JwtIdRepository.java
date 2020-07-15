/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.repository;

import nl.gidsopenstandaarden.ri.module.entity.JwtId;
import org.springframework.data.repository.CrudRepository;

/**
 *
 */
public interface JwtIdRepository extends CrudRepository<JwtId, String> {
}
