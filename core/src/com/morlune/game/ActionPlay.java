package com.morlune.game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.Rectangle;
import com.morlune.game.bicho.ActorBicho;
import com.morlune.game.bicho.SelectorBicho;

import java.util.Iterator;

/**
 * Created by AntonioJoaquín on 20/06/2015.
 */
public class ActionPlay {

    private Array<ActorBicho> Bichos; // lista que contiene bichos
    private ActorBicho bicho;
    private SceneCity city; //objeto de SceneCity
    private MyGdxGame main; //objeto de la clase principal necesario para obtener altura y anchura
    private SelectorBicho selector;

    public ActionPlay(){
        main = new MyGdxGame();
        Bichos = new Array<ActorBicho>(); // instanciar array
        city = new SceneCity(main.getHeight(), main.getWidth()); //inicializamos el objeto ciudad
        selector = new SelectorBicho();
    }

    public Array<ActorBicho> getBichos(){return Bichos;}

    //dibujar a los bichos
    public void drawBicho(Iterator<ActorBicho> iterx, SpriteBatch batch){
        ActorBicho bicho = iterx.next();
        bicho.show(batch);
        }

    // metodo aparecer actor bicho
    public long spawnActorBicho() {
        Bichos.add(selector.selection());						// lo metemos en la array
        return TimeUtils.nanoTime(); // devolvemos el momento en que lo guardamos
    }

    public int movementBicho(SceneCity city, Iterator<ActorBicho> iter, Rectangle RectangleRaton, int Puntos){
        bicho = iter.next();
        bicho.movimiento(); // moviendo bicho

        // si colisiona borramos bicho de la lista
        if (bicho.colision(city.getRectangleCity())){
            iter.remove();
        }
        // si colisiona con el rectangulo y hacemos click lo borramos de la lista
        if (bicho.colision(RectangleRaton) && Gdx.input.isTouched()){
            Puntos = Puntos + bicho.getPoints();
            iter.remove();
        }
        return Puntos;
    }

    public int damage(SceneCity city, int puntos){
        if(bicho.colision(city.getRectangleCity())){
            puntos = city.loseHealth(bicho.getDamage());
            if(puntos <= 50 && puntos > 0) city.setCityTexture(1);
            else if(puntos >= 50) city.setCityTexture(0);
            else{
                city.setCityTexture(2);
                main.dispose();
            }
        }
        return puntos;
    }
}
