/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.generics;

import java.util.ArrayList;

/**
 *
 * @author tss
 */
public class App {

    public static void main(String[] args) {
        Box<Car> box = new Box();
        box.set(new Car("Fiat panda"));
        Car car = box.get();
        
        Box<String> sBox = new Box<>();
        sBox.set("ciao");
        
    }
}

class Box<T> {

    private T object;

    public void set(T object) {
        this.object = object;
    }

    public T get() {
        return object;
    }
}

class Car {

    private String marca;

    public Car(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

}
