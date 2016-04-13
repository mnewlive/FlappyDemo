package ru.topjunior.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Vadim on 08.04.2016.
 */
public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    //конструктор
    public  State(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    //метод, который будет опрашивать пользовательский ввод
    protected  abstract void handleInput ();

    //обновляет картинку, через определенные промежутки времени
    public abstract void update(float dt);

    //рисует экран к которому идёт класс SpriteBatch которые предоставляет текстуру и координаты для рисования фигуры
    public  abstract  void render(SpriteBatch sb);

    //освобождает ресурсы
    public  abstract void dispose();

}
