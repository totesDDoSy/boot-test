package com.csanford.boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Cody Sanford <cody.b.sanford@gmail.com>
 */
@Controller
public class HomeController
{
    // inject via application.properties
    @Value( "${welcome.message}" )
    private String message = "Hello World";

    @RequestMapping( "/" )
    public String display( Model model )
    {
	model.addAttribute( "message", message );
	return "home";
    }
}
