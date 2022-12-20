package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin
public class AvionController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/avions")
    public Object listeAvion() {
//    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
        Vector<Avion> vv=new Vector<>();
        try {
            vv= Avion.selectAllAvion();
        } catch (Exception e) {
            return new Error(e);
        }
            return new Data(vv);
    }

    @GetMapping("/avions/{idv}")
    public Object listeAvion(@PathVariable String idv) {
//    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
        Avion v=new Avion();
        try {
            v= Avion.selectAvion(idv);
        } catch (Exception e) {
            return new Error(e);
        }
            return new Data(v);
    }
    @PostMapping("/avions")
    public Object ajouterAvion(@RequestBody Avion ve){
        try{
            Avion.insertAvion(ve);
            System.out.println("insert ok");
        }catch(Exception e){
            return new Error(e);
        }
        return new Data(ve);
    }
    @PutMapping("/avions/{idvec}")
    public Object modifierAvion(@RequestBody Avion ve, @PathVariable String idvec){
        try{
            Avion.updateAvion(ve,idvec);
            System.out.println("update ok");
        }catch(Exception e){
            return new Error(e);
        }
        return  new Data(ve);
    }
    @DeleteMapping("/avions/{idvec}")
    public Object deleteAvion(@PathVariable String idvec) {
        try {
            Avion.deleteAvion(idvec);
            System.out.println("delete ok");
        } catch (Exception e) {
            return new Error(e);
        }
        return new Data("Avion supprime");
    }
    //public Vector<User> getUser() {
//    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));

}
