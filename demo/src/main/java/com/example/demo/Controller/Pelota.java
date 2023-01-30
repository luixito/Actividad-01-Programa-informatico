package com.example.demo.Controller;

import java.util.Random;
import java.util.Observable;

public class Pelota extends Observable implements Runnable{
    private Posicion pos;
    private String color;
    private int tam;
    private int distanciaX;
    private Random random;
    private int distanciaY;
    private boolean status = true;

    public Pelota() {
        status = true;
        random = new Random(System.currentTimeMillis());
    }

    public void setPosicion(Posicion pos, int v){
        this.pos = pos;
        distanciaX = v;
        distanciaY = v;
    }

    public Posicion getPos() {
        return pos;
    }

    public String getColor() {
        return color;
    }

    public int getTam() {
        return tam;
    }

    public void setStatus(boolean status){
        this.status = status;
    }
    @Override
    public void run() {
        while(status) {
            //Notificar cambio

            //Dormir el hilo
            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Realizar nuevos cálculos
            pos.setX(pos.getX() + distanciaX);
            pos.setY(pos.getY() + distanciaY);
            System.out.println(pos.getX()+":"+pos.getY());

            if (pos.getX() >= 590)
                distanciaX *= -1;
            if (pos.getX() < 10)
                distanciaX *= -1;
            if (pos.getY() < 10)
                distanciaY *= -1;
            if (pos.getY() >= 490)
                distanciaY *= -1;

            this.setChanged();
            this.notifyObservers(pos);

        }
        System.out.println("GoodBay");
    }
}
