package ru.topjunior.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Vadim on 10.04.2016.
 */
public class Animation {

    //массив текстур где буем хранить карды анимации
    private Array<TextureRegion> frames;
    // длительность изображения одного кадра
    private float maxFrameTime;
    // время изображения текущего кадра
    private float currentFrameTime;
    // кол-во кадров анимации
    private int frameCount;
    // отдельный кадр анимации
    private int frame;

    //конструктор где регион текстур, кол-во кадров анимации, длительность кадров анимации
    public Animation(TextureRegion region, int frameCount, float cycleTime){
        // инициализируем массив текстур и поределим ширину кадра
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    //проверяем если длительность изображения текущего кадра больше максимальной, увеличиваем номер кадра, пока число кадров не достигнет установленного и так по кругу
    public void update(float dt){
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount)
            frame = 0;
    }

    // нам нужен текущий кадр анимации
    public  TextureRegion getFrame(){
        return frames.get(frame);
    }
}