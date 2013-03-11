package com.sapient.wmc.rampup.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.io.Resources;

@Controller
public class RefDataController {

	@RequestMapping(value = "reteriveSystemUsers", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public @ResponseBody
	String reteriveSystemUsers() throws IOException {
		URL url = Resources.getResource("reteriveSystemUsers.json");

		return Resources.toString(url, Charset.defaultCharset());
	}
}
