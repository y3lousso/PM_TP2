package com.uqac.controller;

import com.uqac.Compte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.TransformerException;

@Controller
public class BankController {

    private String id = "001";
    private Dal dal;

    @RequestMapping(value = "/bank", method = RequestMethod.GET)
    public ModelAndView displayAccount() {

        if (dal == null)
            dal = new Dal();

        Compte account = new CompteImp(id, dal.getItem("nom", id), dal.getItem("telephone", id), dal.getItem("service", id));
        account.deposer(Double.parseDouble(dal.getItem("solde", id)));

        ModelAndView modelAndView = new ModelAndView("bank", "account", account);
        modelAndView.addObject("accountAction", new AccountAction());

        return modelAndView;
    }

    @RequestMapping(value = "/bank", method = RequestMethod.POST)
    public String accountAction(@ModelAttribute AccountAction accountAction) {

        if (dal == null)
            dal = new Dal();

        try {
            dal.deposer(id, accountAction.getValue());
        } catch (TransformerException e) {
            e.printStackTrace();
        }
//        else if (action.equals("retirer")) {
//            try {
//                dal.retirer(id, value);
//            } catch (TransformerException e) {
//                e.printStackTrace();
//            }
//        }

        return "bank";
    }
}