package ru.topjunior.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.topjunior.game.FlappyDemo;

/**
 * Created by Vadim on 08.04.2016.
 */
public class GameOver extends State {


    //пропишем наши картинки
    private Texture background;
    private Texture gameover;


    //добавим конструктор
    public GameOver(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        //вписываем в текстуры имена файлов
        background = new Texture("bg.png");
        gameover = new Texture("gameover.png");
    }

    // проверяем был ли таб или клик, после условия justTouched и методом set класса убираем вехний экран из стэка и добавляем новый экран на вершину стэка
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        //отрисовка экрана, а именно фон и расположение кнопки play
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(gameover, camera.position.x - gameover.getWidth() / 2, camera.position.y);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();

        System.out.println("GameOver Disposed");

    }
}
