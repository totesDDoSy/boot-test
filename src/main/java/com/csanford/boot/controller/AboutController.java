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
public class AboutController
{
    @Value( "${version.number}" )
    private String version = "0.1";

    @RequestMapping( "/about" )
    public String display( Model model )
    {
	model.addAttribute( "version", version );
	return "about";
    }
}
