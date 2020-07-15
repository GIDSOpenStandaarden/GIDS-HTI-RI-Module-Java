/*
 * Copyright (c) 2020 Headease B.V., This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 */

package nl.gidsopenstandaarden.ri.module.controller;

import nl.gidsopenstandaarden.ri.module.valueobject.HtiUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
	@RequestMapping("current")
	public HtiUser current(HttpSession session) {
		return (HtiUser) session.getAttribute("user");
	}
}
