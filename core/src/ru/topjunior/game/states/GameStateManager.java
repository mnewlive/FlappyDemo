package ru.topjunior.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Vadim on 08.04.2016.
 */
public class GameStateManager {

    private Stack<State> states;

    //конструктор который создаёт простой стек
    public GameStateManager(){
        states = new Stack<State>();
    }

    //помещает элемент в вершину стека
    public void push(State state){
        states.push(state);
    }

    //извлекает верхний элемент удаляя его из стэка, тут же и освобождаем ресурсы
    public void pop(){
        states.pop().dispose();
    }

    //будет удалять из стэка верхний экран, очищать его рес-сы и помещать следующий экран в вершину стэка
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    //peek возвращает верхний элемент, не удаляя его из стэка при этом
    //т.о. метод update обновляет игру, обновляя то игровое состояние , которое находиться на вершине стэка
    public void  update(float dt){
        states.peek().update(dt);
    }

    //принимает состояние из верхней части стека, оставляя его там и отрисовывая
    public void render(SpriteBatch sb){
        states.peek().render(sb);

    }
}
