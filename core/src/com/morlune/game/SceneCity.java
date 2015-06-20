package com.morlune.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by AntonioJoaquín on 20/06/2015.
 */
public class SceneCity {

    //declaramos las variables:
    //textura que tendrá la ciudad
    private Texture cityTexture;
    //la ciudad como objeto físico
    private Sprite city;
    //área de colisión de la ciudad
    private Rectangle rectangleCity;

    public SceneCity(int height, int width){
        cityTexture = new Texture("casa.png");
        // instanciar sprint ciudad
        city = new Sprite(cityTexture);
        // Dar posicion a sprint ciudad
        city.setPosition((width/2)-(city.getWidth())/2, (height/2)-(city.getHeight())/2);
        // instanciar rectangulo colision ciudad
        rectangleCity = new Rectangle((width/2)-city.getWidth()/2,(height/2)-city.getHeight()/2, city.getWidth(), city.getHeight());
    }

    //método para devolver la zona de colisión de la ciudad
    public Rectangle getRectangleCity(){return rectangleCity;}
    //método para devolver el Sprite de la ciudad
    public Sprite getSpriteCity(){return city;}

    public void show(SpriteBatch lienzo) {city.draw(lienzo);}
}