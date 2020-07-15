/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.controller;

import nl.gidsopenstandaarden.ri.module.service.HtiLaunchService;
import nl.gidsopenstandaarden.ri.module.util.UrlUtils;
import nl.gidsopenstandaarden.ri.module.valueobject.HtiUser;
import nl.gidsopenstandaarden.ri.module.valueobject.TaskValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 */
@Controller
@RequestMapping("/module_launch")
public class HtiController {

	@Autowired
	HtiLaunchService htiLaunchService;

	@PostMapping
	public View post(String token, HttpSession session, HttpServletRequest request) {
		TaskValueObject task = htiLaunchService.launch(token, UrlUtils.getServerUrl("", request));
		HtiUser user = new HtiUser();
		user.setReference(task.getForUser().getReference());
		user.setAnonymous(true);
		session.setAttribute("user", user);
		session.setAttribute("task", task);

		return new RedirectView("index.html");
	}
}
