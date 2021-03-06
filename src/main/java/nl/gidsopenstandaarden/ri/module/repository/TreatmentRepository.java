/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.repository;

import nl.gidsopenstandaarden.ri.module.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface TreatmentRepository extends JpaRepository<Treatment, String> {
	Treatment getById(String id);
}
