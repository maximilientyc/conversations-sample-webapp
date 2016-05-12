package com.github.conversations.sample.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by @maximilientyc on 12/05/2016.
 */
@Controller
public class ApplicationController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
