package com.example.snake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GUI extends Application{
    public static int speed = 5;
    public static int width = 20;
    public static int heigth = 20;
    public static int cornerSize = 25;
    public static int foodColor = 0;
    public static int foodX = 0;
    public static int foodY = 0;
    public static Random rand = new Random();
    public static Dir direction = Dir.left;
    public static boolean gameOver = false;

    public static List<Corner> snake = new ArrayList<>();

    public enum Dir{
        left, right, up, down
    }

    public static class Corner{
        int x;
        int y;

        public Corner(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    @Override
    public void start(Stage stage) throws IOException {


        VBox root = new VBox();
        Canvas c = new Canvas(width*cornerSize, heigth*cornerSize);
        GraphicsContext gx = c.getGraphicsContext2D();
        root.getChildren().add(c);

        new AnimationTimer(){
            long lastTick =0;

            public void handle(long now){
                if(lastTick == 0){
                    lastTick = now;

                    return;
                }if(now - lastTick > 1000000000 / speed)
                    lastTick = now;
            }
        }.start();




        Scene scene = new Scene(root, width*cornerSize, heigth*cornerSize);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, key ->{
            if(key.getCode() == KeyCode.W){
                direction = Dir.up;
            }if(key.getCode() == KeyCode.A){
                direction = Dir.left;
            }if(key.getCode() == KeyCode.S){
                direction = Dir.down;
            }if(key.getCode() == KeyCode.D){
                direction = Dir.right;
            }
        });


        stage.setTitle("EPIC SNAKE ROYALE");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    public static void newFood(){
        start: while(true){
            foodX = rand.nextInt(width);
            foodY = rand.nextInt(heigth);

            for(Corner c : snake){
                if(c.x == foodX && c.y == foodY){
                    continue start;
                }
            }
            foodColor = rand.nextInt(5);
            speed++;
            break;
        }
    }

    public static void snakeStart(){
        snake.add(new Corner(width/2, heigth/2));
        snake.add(new Corner(width/2, heigth/2));
        snake.add(new Corner(width/2, heigth/2));
    }

    public static void tick(GraphicsContext gc){
        if(gameOver){

            return;
        }

        for(int i = snake.size() -1 ; i>=1; i--){
            snake.get(i).x = snake.get(i-1).x;
            snake.get(i).y = snake.get(i-1).y;
        }

        switch(direction){
            case up:
                snake.get(0).y--;
                if (snake.get(0).y < 0){
                    gameOver = true;
                }
            case down:
                snake.get(0).y++;
                if(snake.get(0).y < 0){
                    gameOver = true;
                }
        }

    }
}