/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import nl.gidsopenstandaarden.ri.module.controller.TreatmentController;
import nl.gidsopenstandaarden.ri.module.entity.Treatment;
import nl.gidsopenstandaarden.ri.module.repository.TreatmentRepository;
import nl.gidsopenstandaarden.ri.module.util.UrlUtils;
import nl.gidsopenstandaarden.ri.module.valueobject.HtiUser;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
public class TreatmentService {


	private final TreatmentRepository treatmentRepository;

	private final ResourceLoader resourceLoader;

	public TreatmentService(TreatmentRepository treatmentRepository, ResourceLoader resourceLoader) {
		this.treatmentRepository = treatmentRepository;
		this.resourceLoader = resourceLoader;
	}

	public Treatment getTreatment(String id) {
		return treatmentRepository.findById(id).orElse(null);
	}

	@PostConstruct
	public void init() throws IOException {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.findAndRegisterModules();

		TreatmentController.Treatments treatments = mapper.readValue(resourceLoader.getResource("classpath:treatments.yaml").getURL(), TreatmentController.Treatments.class);
		for (Treatment treatment : treatments.getTreatments()) {
			Optional<Treatment> optional = treatmentRepository.findById(treatment.getId());
			if (!optional.isPresent()) {
				treatmentRepository.save(treatment);
			}
		}
	}

}
