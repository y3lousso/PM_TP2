package com.uqac.controller;

import com.uqac.Compte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.TransformerException;

@Controller
public class BankController {

    private String id = "001";
    private Dal dal;

    @GetMapping(value = "/bank")
    public ModelAndView displayAccount() {

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

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bank");
        modelAndView.addObject("account", account);

        return modelAndView;
    }
    
    @ModelAttribute("accountAction")
    public AccountAction formBackingObject() {
        return new AccountAction();
    }

    @PostMapping(value="/bankPost")
    public String accountAction(@ModelAttribute("accountAction") AccountAction accountAction, BindingResult result, ModelMap model) {

    	if (result.hasErrors()) 
    	{
            return "error";
        }
        
        if (dal == null) 
        {
            dal = new Dal();
        }     	        
        
        //dal.deposer(id, accountAction.getValue());       

        return "redirect:bank.html";
    }
}