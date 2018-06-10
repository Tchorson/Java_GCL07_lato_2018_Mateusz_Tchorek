package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Controller implements Initializable {


    @FXML
    public Canvas canvas;



    @FXML
    private void handleButtonAction(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GraphicsContext gc = canvas.getGraphicsContext2D();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.setStroke(Color.BLACK);
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.strokeOval(20, 20, 205, 205);
                double size = canvas.getWidth();
                double center = size/2;
                double time = (int)System.currentTimeMillis();
                double anim, anim2;
                double radius = size-120;
                time = ((time+2500)%60000/60000.0*Math.PI*2);
                anim= (int) ((center-10)+(Math.sin(time))*radius/2);
                anim2= (int) ((center-10)-(Math.cos(time))*radius/2);
                gc.setStroke(Color.RED);
                gc.strokeLine(center-10, center-10, anim, anim2);
                time = (int)System.currentTimeMillis();
                time = ((time+1560000)%3600000/3600000.0*Math.PI*2);
                anim= (int) ((center-10)+(Math.sin(time))*radius/2);
                anim2= (int) ((center-10)-(Math.cos(time))*radius/2);
                gc.setStroke(Color.BLACK);
                gc.strokeLine(center-10, center-10, anim, anim2);
                time = (int)System.currentTimeMillis();
                time = ((time+1000000)%43200000/43200000.0*Math.PI*2);
                radius-=40;
                anim= (int) ((center-10)+(Math.sin(time))*radius/2);
                anim2= (int) ((center-10)-(Math.cos(time))*radius/2);
                gc.setStroke(Color.BLACK);
                gc.strokeLine(center-10, center-10, anim, anim2);
                for(int i=0;i<60;i++){
                    int x1,x2,x3,x4;
                    radius=size-60;
                    x1= (int) ((center-10)+(Math.sin(i/60.0*Math.PI*2))*radius/2);
                    x2= (int) ((center-10)-(Math.cos(i/60.0*Math.PI*2))*radius/2);
                    if(i%5==0){
                        radius=size-80;
                    }
                    else{
                        radius=size-70;
                    }
                    x3= (int) ((center-10)+(Math.sin(i/60.0*Math.PI*2))*radius/2);
                    x4= (int) ((center-10)-(Math.cos(i/60.0*Math.PI*2))*radius/2);
                    gc.strokeLine(x1,x2,x3,x4);


                }
                for(int i=0;i<12;i++){
                    int x1,x2;
                    radius=size-100;
                    x1= (int) ((center-15)+(Math.sin((i+1)/12.0*Math.PI*2))*radius/2);
                    x2= (int) ((center-5)-(Math.cos((i+1)/12.0*Math.PI*2))*radius/2);
                    gc.fillText(Integer.toString(i+1), x1, x2);
                }
            }
        };
        timer.start();

    }

}
