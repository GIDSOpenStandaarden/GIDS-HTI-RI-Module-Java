/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.controller;

import nl.gidsopenstandaarden.ri.module.exception.NotLoggedInException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 *
 */
@Controller
public class IndexController {
	@GetMapping("/")
	public String index(HttpSession session){
		if (session.getAttribute("user") != null) {
			return "index.html";
		} else {
			throw new NotLoggedInException("Please use HTI to launch this module.");
		}

	}
}
