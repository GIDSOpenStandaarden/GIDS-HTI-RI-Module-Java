/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 *
 */
@SpringBootApplication
@EntityScan("nl.gidsopenstandaarden.ri.module.entity")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(nl.gidsopenstandaarden.ri.module.Application.class, args);
	}
}
