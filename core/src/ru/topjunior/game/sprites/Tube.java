package ru.topjunior.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Vadim on 09.04.2016.
 */
public class Tube {

    //диапазон отклонений создания труб
    public static final int FLUCTUATION = 130;
    //значение высоты просвета
    public static final int TUBE_GAP = 100;
    //нижняя высота просвета
    public static final int LOWEST_OPENING = 120;
    public static final int TUBE_WIDTH = 52;


    private Texture topTube,  bottomTube;
    //vector2 так как трубы будут двигаться
    private Vector2 posTopTube, posBotTube;
    //трубы на разной высоте
    private Random rand;
    //нарисуем невидымые прямоугольнки вокруг наших труб
    private Rectangle boundsTop, boundsBot;

    //выбираем текстуры и трубы, создаём getteri для того чтобы иметь доступ к этим переменным из других классов
    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    //конструктор, где присваиваем картинки нашим 2-м трубам
    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();

        //создание наших труб, где верхняя создаётся random а вторая всегда будет подстраиваться, чтоб просвет был одинакомый
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        //будем создавать их в конструкторе
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());

    }

    //движение труб
    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        //задаём начальное расположение, совпадающее с расположением труб
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
    }

    //метод столкновения птицы с трубами
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
