/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Configuration
@ConfigurationProperties(prefix = "hti")
public class HtiConfiguration {

	public List<String> allowedPortals = new ArrayList<>();

	public List<String> getAllowedPortals() {
		return allowedPortals;
	}

	public void setAllowedPortals(List<String> allowedPortals) {
		this.allowedPortals = allowedPortals;
	}

}
