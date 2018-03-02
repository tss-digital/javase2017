/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.generics.esempi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author tss
 */
public class App2 {

    public static void main(String[] args) {
        Box<Car> box1 = new Box<>();
        box1.set(new Car("fiat"));

        Box<Car> box2 = new Box<>();
        box2.set(new Car("Bmw"));

        System.out.println(box1.compareTo(box2));

        List<Box> boxs = new ArrayList<>();
        boxs.add(box1);
        boxs.add(box2);

        Collections.sort(boxs);
        
        System.out.println(boxs);
        
        mioSort(boxs);
    }
    
    private static void mioSort(Collection<? extends Comparable> insieme){
        
        
    }
}




class Box<T extends Comparable> implements Comparable<Box> {

    private T object;

    public void set(T object) {
        this.object = object;
    }

    public T get() {
        return object;
    }

    @Override
    public int compareTo(Box o) {
        return object.compareTo(o.get());
    }

    @Override
    public String toString() {
        return object.toString();
    }

    
}

class Car implements Comparable<Car> {

    private String marca;

    public Car(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public int compareTo(Car o) {
        return marca.compareTo(o.getMarca());
    }

    @Override
    public String toString() {
        return marca;
    }

    
}
