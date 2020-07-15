/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.service;

import nl.gidsopenstandaarden.ri.module.configuration.HtiConfiguration;
import nl.gidsopenstandaarden.ri.module.exception.InvalidIssuerException;
import nl.gidsopenstandaarden.ri.module.exception.InvalidTokenException;
import nl.gidsopenstandaarden.ri.module.repository.JwtIdRepository;
import nl.gidsopenstandaarden.ri.module.util.KeyUtils;
import nl.gidsopenstandaarden.ri.module.valueobject.HtiPortal;
import org.jose4j.jwk.HttpsJwks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;

/**
 *
 */
@Service
public class HtiPortalService {

	final HtiConfiguration htiConfiguration;
	final JwksDiscoveryService jwksDiscoveryService;
	public HtiPortalService(HtiConfiguration htiConfiguration, JwksDiscoveryService jwksDiscoveryService) {
		this.htiConfiguration = htiConfiguration;
		this.jwksDiscoveryService = jwksDiscoveryService;
	}

	public HtiPortal getPortal(String token)  {
		String issuer = jwksDiscoveryService.getIssuer(token);
		if (htiConfiguration.getAllowedPortals().contains(issuer)) {
			Key key = jwksDiscoveryService.discoverPublicKeyWithJwks(token);
			if (key != null) {
				HtiPortal portal = new HtiPortal();
				portal.setIssuer(issuer);
				portal.setPublicKey(KeyUtils.encodeKey(key));
				return portal;
			} else {
				throw new InvalidIssuerException(String.format("Cannot find public key on url %s", jwksDiscoveryService.getJwksUrl(token)));
			}
		}

		throw new InvalidIssuerException(String.format("Issuer %s is not allowed", issuer));
	}

}
