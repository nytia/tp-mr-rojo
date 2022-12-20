package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Vector;

@RestController
@CrossOrigin
public class AssuranceController extends HttpServlet {
    @GetMapping("/assurances1mois")
    public Object listeAssurance1Mois() {
        Vector<Assurance> vv=new Vector<>();
        try {
            vv=Assurance.selectAssurance1Mois();
        } catch (Exception e) {
            return new Error(e);
        }
        return new Data(vv);
    }
    @GetMapping("/assurances3mois")
    public Object listeAssurance3Mois() {
        Vector<Assurance> vv=new Vector<>();
        try {
            vv=Assurance.selectAssurance3Mois();
        } catch (Exception e) {
            return new Error(e);
        }
        return new Data(vv);
    }
}
