/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.gidsopenstandaarden.ri.module.configuration.HtiConfiguration;
import nl.gidsopenstandaarden.ri.module.exception.InvalidTokenException;
import nl.gidsopenstandaarden.ri.module.repository.JwtIdRepository;
import nl.gidsopenstandaarden.ri.module.util.KeyUtils;
import nl.gidsopenstandaarden.ri.module.valueobject.HtiPortal;
import nl.gidsopenstandaarden.ri.module.valueobject.HtiUser;
import nl.gidsopenstandaarden.ri.module.valueobject.TaskValueObject;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.*;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;

/**
 *
 */
@Service
public class HtiLaunchService {
	final HtiPortalService htiPortalService;
	final HtiConfiguration htiConfiguration;
	final JwtIdRepository jwtIdRepository;

	public HtiLaunchService(HtiPortalService htiPortalService, HtiConfiguration htiConfiguration, JwtIdRepository jwtIdRepository) {
		this.htiPortalService = htiPortalService;
		this.htiConfiguration = htiConfiguration;
		this.jwtIdRepository = jwtIdRepository;
	}

	public TaskValueObject launch(String token, String host) throws InvalidTokenException {
		try {
			HtiPortal portal = htiPortalService.getPortal(token);
			JwtConsumer jwtConsumer = new JwtConsumerBuilder()
					.setExpectedIssuer(portal.getIssuer())
					.setExpectedAudience(host)
					.setVerificationKey(KeyUtils.getRsaPublicKey(portal.getPublicKey()))
					.setRequireJwtId()
					.build();
			JwtContext jwtContext = jwtConsumer.process(token);
			JwtClaims jwtClaims = jwtContext.getJwtClaims();
			String jwtId = jwtClaims.getJwtId();
			if (jwtIdRepository.existsById(jwtId)) {
				throw new InvalidTokenException("Failed to validate the jti field");
			}
			ObjectMapper objectMapper = new ObjectMapper();
			TaskValueObject task = objectMapper.convertValue(jwtClaims.getClaimValue("task"), TaskValueObject.class);
			return task;
		} catch (GeneralSecurityException | InvalidJwtException | MalformedClaimException e) {
			throw new InvalidTokenException(e);
		}
	}

}
