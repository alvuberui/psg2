package org.springframework.samples.petclinic.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/support")
public class SupportController {

    private String VIEW = "support";
    
    @GetMapping()
    public String showSupport(ModelMap modelMap, HttpServletRequest request){
        return VIEW;
    }

}
