/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.controller;

import nl.gidsopenstandaarden.ri.module.entity.Treatment;
import nl.gidsopenstandaarden.ri.module.exception.NotLoggedInException;
import nl.gidsopenstandaarden.ri.module.service.TreatmentService;
import nl.gidsopenstandaarden.ri.module.valueobject.HtiUser;
import nl.gidsopenstandaarden.ri.module.valueobject.TaskValueObject;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
@RestController
@RequestMapping("/api/treatment")
public class TreatmentController {

	private TreatmentService treatmentService;

	@Autowired
	public void setTreatmentService(TreatmentService treatmentService) {
		this.treatmentService = treatmentService;
	}

	@GetMapping("current")
	public Treatment treatment(HttpSession session) {
		TaskValueObject task = (TaskValueObject) session.getAttribute("task");
		if (task == null) {
			throw new NotLoggedInException("No active task found");
		}
		String reference = task.getDefinitionReference().getReference();
		 // "TaskDefinition/" + treatment.getId()
		String id = getIdFromReference(reference);
		return treatmentService.getTreatment(id);
	}

	/**
	 *
	 * @param reference
	 * @return
	 */
	private String getIdFromReference(String reference) {
		Pattern pattern = Pattern.compile("TaskDefinition/(.*)");
		Matcher matcher = pattern.matcher(reference);
		if (matcher.matches()) {
			return matcher.group(1);
		}
		return null;
	}

	public static class Treatments {
		List<Treatment> treatments;

		public List<Treatment> getTreatments() {
			return treatments;
		}

		public void setTreatments(List<Treatment> treatments) {
			this.treatments = treatments;
		}
	}
}
