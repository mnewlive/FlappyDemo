package ru.topjunior.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Vadim on 09.04.2016.
 */
public class Bird {
    private static final int GRAVITY = -15;
    //хранят скорость и позицию птички
    private Vector3 position;
    private Vector3 velosity;
    // перемещение птицы пункт 1
    private static final int MOVEMENT = 100;
    // создаём прямоуголник вокруг птицы
    private Rectangle bounds;
    private Animation birdAnimation;
    // звуки самой птицы
    private Sound flap;
    private Texture texture;

    //конструктор, который задаёт позицию и время движение + текстура
    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        //bird = new Texture("bird.png");
        texture = new Texture("birdanimation.png");
        //кадров анимации 3 длительность пол секунды
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    //сила тяжести реализуем через gravity = -15, далее формула в add
    public void update(float dt) {
        birdAnimation.update(dt);
        if (position.y >0)
            velosity.add(0, GRAVITY, 0);
        velosity.scl(dt);
        // перемещение птицы пункт 2
        position.add(MOVEMENT * dt, velosity.y, 0);

        // сделаем чтоб не падала ниже нижней границы + добавим вверх условие  if (position.y >0)
        if (position.y < 0)
            position.y = 0;

        velosity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);

    }

    //присваиваем 250 y вектора скорости
    public void jump(){
        velosity.y = 250;
        flap.play();
    }

    // создаём гетер для доступа из других классов
    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();

    }
}
