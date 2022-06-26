package org.springframework.samples.petclinic.changelog;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/changelog")
public class ChangelogController {

    private String VIEW = "changelog";
    
    @GetMapping()
    public String showChangelog(ModelMap modelMap, HttpServletRequest request){
        return VIEW;
    }

}
