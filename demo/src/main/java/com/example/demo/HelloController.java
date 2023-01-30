package com.example.demo;

import com.example.demo.Controller.Pelota;
import com.example.demo.Controller.Posicion;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class HelloController implements Observer {
    private final Random ran = new Random();
    private int ranx;
    private int rany;
    @FXML
    private AnchorPane rootScene;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnSalir;
    @FXML
    private Circle pelota;
    private Circle v2;
    private Circle v3;
    private Pelota c2;
    private Pelota c4;
    private Circle v4;
    private Circle v5;
    private Pelota c5;
    private Pelota c3;

    //entrada nave
    private int move =15;
    private ImageView nave;

    @FXML
    private Button BtnDerecha;
    @FXML
    private Button BtnIzquierda;


    @FXML
    void BtnDerecha(MouseEvent event) {
        nave.setLayoutX(nave.getLayoutX()+move);
    }
    @FXML
    void BtnIzquierda(MouseEvent event) {
        nave.setLayoutX(nave.getLayoutX()-move);
    }
    @FXML
    void btnCrearOnMouse(MouseEvent event) {
        //pelotita = new Pelota(1, "Blue", 10);
        //pelotita.addObserver(this);
        //pelotita.setPosicion();
        //Thread hilo01 = new Thread(pelotita);
        //hilo01.setDaemon(true);
        //hilo01.start();

        v2 = new Circle(20, Color.BLACK);
        ranx = ran.nextInt(600);
        rany = ran.nextInt(350);
        v2.setLayoutX(ranx);
        v2.setLayoutY(rany);
        //Circulo 3

        c2 = new Pelota();
        c2.setPosicion(new Posicion(2, ranx, rany), 7);
        c2.addObserver(this);
        new Thread(c2).start();

        ranx = ran.nextInt(600);
        rany = ran.nextInt(350);
        v3 = new Circle(12, Color.BLACK);
        v3.setLayoutX(ranx);
        v3.setLayoutY(rany);

        c3 = new Pelota();
        c3.setPosicion(new Posicion(3, ranx, rany), 10);
        c3.addObserver(this);
        new Thread(c3).start();

        ranx = ran.nextInt(600);
        rany = ran.nextInt(350);
        v4 = new Circle(15, Color.YELLOW);
        v4.setLayoutX(ranx);
        v4.setLayoutY(rany);

        c4 = new Pelota();
        c4.setPosicion(new Posicion(4, ranx, rany), 6);
        c4.addObserver(this);
        new Thread(c4).start();

        ranx = ran.nextInt(600);
        rany = ran.nextInt(350);
        v5 = new Circle(8, Color.BLUE);
        v5.setLayoutX(ranx);
        v5.setLayoutY(rany);

        c5 = new Pelota();
        c5.setPosicion(new Posicion(5, ranx, rany), 7);
        c5.addObserver(this);
        new Thread(c5).start();
        rootScene.getChildren().addAll(v2, v3,v4,v5);

    }

    @FXML
    void btnDetener(MouseEvent event) {
        nave = new ImageView(new Image(getClass().getResourceAsStream("/assets/img/nave.png")));
        nave.setFitWidth(45);
        nave.setFitHeight(60);
        nave.setLayoutX(300);
        nave.setLayoutY(380);
        rootScene.getChildren().addAll(nave);
    }


    @FXML
    void btnSalirOnMouse(MouseEvent event) {
        System.exit(1);
    }

    @Override
    public void update(Observable o, Object arg) {
        Posicion pos = (Posicion) arg;
        System.out.println(pos.getId());
        switch (pos.getId()) {
            case 2:
                System.out.println("llegamos1");
                Platform.runLater(() -> v2.setLayoutX(pos.getX()));
                Platform.runLater(() -> v2.setLayoutY(pos.getY()));
                break;
            case 3:
                System.out.println("llegamos2");
                Platform.runLater(() -> v3.setLayoutX(pos.getX()));
                Platform.runLater(() -> v3.setLayoutY(pos.getY()));
                break;
            case 4:
                System.out.println("llegamos4");
                Platform.runLater(() -> v4.setLayoutX(pos.getX()));
                Platform.runLater(() -> v4.setLayoutY(pos.getY()));
                break;
            case 5:
                System.out.println("llegamos5");
                Platform.runLater(() -> v5.setLayoutX(pos.getX()));
                Platform.runLater(() -> v5.setLayoutY(pos.getY()));
                break;
        }
        if (nave.getBoundsInParent().intersects(v2.getBoundsInParent())){
            c2.setStatus(false);
            c3.setStatus(false);
            c4.setStatus(false);
            c5.setStatus(false);

        }
        if (nave.getBoundsInParent().intersects(v3.getBoundsInParent())){
            c2.setStatus(false);
            c3.setStatus(false);
            c4.setStatus(false);
            c5.setStatus(false);
        }
        if (nave.getBoundsInParent().intersects(v4.getBoundsInParent())){
            c2.setStatus(false);
            c3.setStatus(false);
            c5.setStatus(false);
            c4.setStatus(false);
        }
        if (nave.getBoundsInParent().intersects(v5.getBoundsInParent())){
            c2.setStatus(false);
            c3.setStatus(false);
            c4.setStatus(false);
            c5.setStatus(false);
        }
    }
}
