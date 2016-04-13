package ru.topjunior.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.topjunior.game.FlappyDemo;

/**
 * Created by Vadim on 08.04.2016.
 */
public class MenuState extends State {


    //пропишем наши картинки
    private Texture background;
    private Texture playBtn;


    //добавим конструктор
    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        //вписываем в текстуры имена файлов
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
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
        sb.draw(playBtn, camera.position.x - playBtn.getWidth() / 2, camera.position.y);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();

        System.out.println("MenuState Disposed");

    }
}
