package ru.topjunior.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.topjunior.game.states.GameStateManager;
import ru.topjunior.game.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Flappy Demo";

	private GameStateManager gsm;
	private SpriteBatch batch;

	private Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		// вызов меню, который создаёт новый экран меню повышая его в стэк
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//метод который обновляет верхний элемент в стэке(экран который виден пользователю)
		//методу передаём парамент Gdx.graphics.getDeltaTime который возвращает время прошедшее между последним и текущим кадром в секунду
		gsm.update(Gdx.graphics.getDeltaTime());
		//отрисовка вернхего экрана в стеке
		gsm.render(batch);


		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
}
