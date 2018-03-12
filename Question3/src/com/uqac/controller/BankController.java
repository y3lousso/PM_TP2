package com.uqac.controller;

import com.uqac.Compte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.TransformerException;
import javax.xml.ws.Action;

@Controller
public class BankController {

    private String id = "001";
    private Dal dal;

    @GetMapping(value = "/bank")
    public String displayAccount(ModelMap model) {

        if (dal == null) 
        {
            dal = new Dal();
        }

        Compte account = new CompteImp(
        		id, 
        		dal.getItem("nom", id), 
        		dal.getItem("telephone", id), 
        		dal.getItem("service", id), 
        		Double.parseDouble(dal.getItem("solde", id)));

        model.addAttribute("account", account);
        model.addAttribute("accountAction", new AccountAction());

        return "bank";
    }

    @RequestMapping(value = "/accountAction", method = RequestMethod.POST)
    public String accountAction(@ModelAttribute("accountAction") AccountAction accountAction) {
        
        if (dal == null) 
        {
            dal = new Dal();
        }

        try {
            if (accountAction.getAction().equals("deposer"))
                dal.deposer(id, accountAction.getValue());
            else if (accountAction.getAction().equals("retirer"))
                dal.retirer(id, accountAction.getValue());
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return "redirect:/bank";
    }
}