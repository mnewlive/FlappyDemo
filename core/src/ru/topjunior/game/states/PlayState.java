package ru.topjunior.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import ru.topjunior.game.FlappyDemo;
import ru.topjunior.game.sprites.Bird;
import ru.topjunior.game.sprites.Tube;

/**
 * игровой экран
 */
public class PlayState extends State {

    //расстояние по ширине между трубами
    public static final int TUBE_SPACING = 125;
    //4 комплекта труб, которые будут появлятся последовательно
    public static final int TUBE_COUNT = 4;
    // константа для того чтобы опустить землю
    public static final int GROUND_Y_OFFSET = -30;

    private Bird bird;
    //добавляем фон
    private Texture bg;
    private Tube tube;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    //массив труб
    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        //bird = new Texture("bird.png");

        bird = new Bird(50, 300);

        //область обзора для камеры, чтобы птичка была норм размеров
        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        //tube = new Tube(100);

        tubes = new Array<Tube>();

        //цикл создания труб до кол-ва TUBE_COUNT
        for (int i = 0; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    //вызов метода jump по табу на экране
    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
        bird.jump();

    }

    @Override
    public void update(float dt) {
        //вызов метода handleInput
        handleInput();
        updateGround();
        bird.update(dt);
        //привязываем позицию камеры к позици птицы, чтобы птица не улетела за экран, а камера двигалась вместе с ней
        camera.position.x = bird.getPosition().x + 80;

        //заставляем трубы двигаться при помощи камеры
        //for(Tube tube : tubes) {
        for (int i = 0; i < tubes.size; i++) {

            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosBotTube().x
                    + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
// обрабатывает столкновения, где при столкновении перезапускает игру
            if (tube.collides(bird.getBounds()))
                gsm.set(new GameOver(gsm));
        }

        //вызывает метод обновления камеры
         camera.update();

    }

    //отрисуем птичку
    @Override
    public void render(SpriteBatch sb) {
        //матрица проекции
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        //рисуем фон, чтоб он распологался по центру
        sb.draw(bg, camera.position.x - (camera.viewportHeight / 2), 0);
        // передаём из класса Bird отрисовку её позиции
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosBotTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();

    }


    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes)
            tube.dispose();
        System.out.println("PlayState Disposed");

    }

    private void updateGround(){
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);
    }
}
