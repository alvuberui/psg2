package org.springframework.samples.petclinic.util;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Service
public class SecurityService {

	private static final Logger logger = Logger.getLogger("MyLog");
	
    @Autowired
    private OwnerService ownerService;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    public User getCurrentUser() {
        return (User) getAuthentication().getPrincipal();
    }

    public boolean isAdmin() {
        return getAuthentication().getAuthorities().stream().anyMatch(x -> x.toString().equals("admin"));
    }

    public boolean isOwner() {
        Authentication authentication = getAuthentication();
        if(authentication != null) {
            return authentication.getAuthorities().stream().anyMatch(x -> x.toString().equals("owner"));
        }
        return false;
    }
    
    public void insertOwnerIdInModel(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.log(java.util.logging.Level.INFO, "Authentication", authentication.getPrincipal());
        User currentUser = (User) authentication.getPrincipal();
        int ownerLoggedId = ownerService.getOwnerIdByUsername(currentUser.getUsername());
        modelMap.put("ownerId",ownerLoggedId);
    }

}
