package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@CrossOrigin
public class KilometrageController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/kilometrages")
    public Object listeKilometrage() {
//    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
        Vector<Kilometrage> vv=new Vector<>();
        try {
            vv=Kilometrage.selectAllKilometrage();
        } catch (Exception e) {
            new Error(e);
        }
        return new Data(vv) ;
    }

    @GetMapping("/kilometrages/{idv}")
    public Object listeKilometrage(@PathVariable String idv) {
//    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
        Kilometrage v=new Kilometrage();
        try {
            v=Kilometrage.selectKilometrage(idv);
        } catch (Exception e) {
            new Error(e);
        }
        return new Data(v) ;
    }
    @PostMapping("/kilometrages")
    public Object ajouterKilometrage(@RequestBody Kilometrage ve){
        try{
            Kilometrage.insertKilometrage(ve);
            System.out.println("insert ok");
        }catch(Exception e){
            new Error(e);
        }
        return new Data(ve) ;
    }
    @PutMapping("/kilometrages/{idvec}")
    public Object modifierKilometrage(@RequestBody Kilometrage ve,@PathVariable String idvec){
        try{
            Kilometrage.updateKilometrage(ve,idvec);
            System.out.println("update ok");
        }catch(Exception e){
            new Error(e);
        }
        return new Data(ve) ;
    }
    @DeleteMapping("/kilometrages/{idvec}")
    public Object deleteKilometrage(@PathVariable String idvec) {
        try {
            Kilometrage.deleteKilometrage(idvec);
            return new Data("kilometrage supprime");
        } catch (Exception e) {
            return new Error(e);
        }
    }
    //public Vector<User> getUser() {
//    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));

}
