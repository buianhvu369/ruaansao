package buianhvu.pac;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

import static com.badlogic.gdx.Gdx.input;

public class GameScreen implements Screen {
    OrthographicCamera camera;
    Master game;
    Stage stage;
    Texture nen;
    Turtle turtle;
    Solid rock;
    Solid rock2;
    Solid rock3;
    Solid rock4;
    Solid sigh;
    Solid sigh2;
    StarFish baoquan;
    StarFish haiquan;
    StarFish bao;
    StarFish sao;
    StarFish nawn;
    Shark tank;
    ShapeRenderer shapeRenderer;
    Random ran;
    An an;
    Sound dropSound;
    Sound lasersound;
    Music nenMusic;
    Texture rockImage;
    Texture sighImage;
    Texture win;
    Texture gameover;
    Texture pressc;
    Texture soundButtonImage;
    int starfish = 5;
    float cd = 0;
    Array<Laze> lasers;
    Boolean kt = false;
    Boolean audio = true;
    Array <Shark> sharks;
    public GameScreen(Master game){
        this.game = game;
        stage = new Stage();
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        ran = new Random();

        kt = false;
        starfish = 5;

        lasers = new Array<>();
        sharks = new Array<>();

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop1.ogg"));
        lasersound = Gdx.audio.newSound(Gdx.files.internal("sfx_laser1.ogg"));
        nenMusic = Gdx.audio.newMusic(Gdx.files.internal("Master_of_the_Feast.ogg"));

        nen = new Texture("water-border.jpg");
        rockImage = new Texture("rock.png");
        sighImage = new Texture("sign.png");
        win = new Texture("you-win.png");
        gameover = new Texture("game-over.png");
        pressc = new Texture("message-continue.png");
        soundButtonImage = new Texture("audio.png");

        rock = new Solid(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight()-64),stage,rockImage,true);
        rock2 = new Solid(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage,rockImage,true);
        rock3 = new Solid(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage,rockImage,true);
        rock4 = new Solid(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage,rockImage,true);
        sigh = new Solid(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage,sighImage,false);
        sigh2 = new Solid(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage,sighImage,false);
        turtle = new Turtle(new Texture("sprite.png"),3,2,stage);
        baoquan = new StarFish(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage) ;
        haiquan = new StarFish(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage) ;
        bao = new StarFish(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage) ;
        nawn = new StarFish(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage) ;
        sao = new StarFish(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64),stage) ;
        tank = new Shark(Gdx.graphics.getWidth() - 100,Gdx.graphics.getHeight() - 262,stage);
        rock.polygon.setPosition(rock.getX(),rock.getY());
        rock2.polygon.setPosition(rock2.getX(),rock2.getY());
        rock3.polygon.setPosition(rock3.getX(),rock3.getY());
        rock4.polygon.setPosition(rock4.getX(),rock4.getY());
        sigh .polygon.setPosition(sigh .getX(),sigh .getY());
        sigh2.polygon.setPosition(sigh2.getX(),sigh2.getY());
        turtle.polygon.setPosition(turtle .getX(),turtle .getY());
        tank.polygon.setPosition(tank.getX(),tank.getY());
        stage.act();
        System.out.println(kt);


//        game.layout = new GlyphLayout();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;
        style.fontColor = Color.WHITE;
        style.up = new TextureRegionDrawable(soundButtonImage);
        TextButton startButton = new TextButton( "",style);
        startButton.setPosition(Gdx.graphics.getWidth() - startButton.getWidth() - 5,
            Gdx.graphics.getHeight() - startButton.getHeight() - 5) ;
        stage.addActor(startButton);
        Gdx.input.setInputProcessor(stage);
        startButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                if(audio){
                    audio = false;
                }else{
                    audio = true;
                }
            }
        });

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(nen, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.font.draw(game.batch, "starfish:" + String.valueOf(starfish), 0, Gdx.graphics.getHeight());
        if (starfish < 1) {
            game.batch.draw(win, Gdx.graphics.getWidth() / 2 - win.getWidth() / 2, Gdx.graphics.getHeight() / 2 - win.getHeight() / 2);
            game.batch.draw(pressc, Gdx.graphics.getWidth() / 2 - pressc.getWidth() / 2, Gdx.graphics.getHeight() / 2 - pressc.getHeight() / 2 - gameover.getHeight() - 10);
            kt = true;
            stage.clear();
        }
        if (Intersector.overlapConvexPolygons(turtle.getPolygon(), tank.getPolygon())) {
            game.batch.draw(gameover, Gdx.graphics.getWidth() / 2 - gameover.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gameover.getHeight() / 2 + 10);
            game.batch.draw(pressc, Gdx.graphics.getWidth() / 2 - pressc.getWidth() / 2, Gdx.graphics.getHeight() / 2 - pressc.getHeight() / 2 - gameover.getHeight() - 10);
            kt = true;
            stage.clear();
        }
        game.batch.end();
        if(kt == false){
            cd += 1/60f;
            if(audio){
                nenMusic.play();
            }
            for (Shark thuy : sharks) {
                if (Intersector.overlapConvexPolygons(turtle.getPolygon(), thuy.getPolygon())) {
                    kt = true;
                    tank.setPosition(turtle.getX(),turtle.getY());
                }
            }
            if(input.isButtonJustPressed(Input.Keys.Q)){

            }
            if (input.isKeyJustPressed(Input.Keys.SPACE)) {
                Laze laser = new Laze(0, 0, stage, new Texture("laser.png"));
                laser.setPosition(turtle.getX() + turtle.getWidth() / 2 - laser.getWidth() / 2, turtle.getY() + turtle.getHeight() / 2 - laser.getHeight() / 2);
                laser.setRotation(turtle.getRotation());
                lasersound.play();
                lasers.add(laser);

                Laze laser2 = new Laze(0, 0, stage, new Texture("laser.png"));
                laser2.setPosition(turtle.getX() + turtle.getWidth() / 2 - laser2.getWidth() / 2, turtle.getY() + turtle.getHeight() / 2 - laser2.getHeight() / 2);
                laser2.setRotation(turtle.getRotation());
                laser2.rotateBy(180);
                lasersound.play();
                lasers.add(laser2);
                starfish += 2;
            }
            for (Shark thuy : sharks) {
                if(tank.dan == 1){
                    thuy.dan = 1;
                    thuy.duoi(turtle.getX(), turtle.getY());
//                    thuy.rotateBy(-ran.nextInt(0,360));
                }
            }
            for (Laze laser : lasers) {
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), rock.getPolygon())) {
                    rock.setPosition(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64));
                    laser.remove();
                }
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), rock2.getPolygon())) {
                    rock2.setPosition(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64));
                    laser.remove();
                }
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), rock3.getPolygon())) {
                    rock3.setPosition(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64));
                    laser.remove();
                }
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), rock4.getPolygon())) {
                    rock4.setPosition(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64));
                    laser.remove();
                }
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), sigh.getPolygon())) {
                    sigh.setPosition(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64));
                    laser.remove();
                }
                if (Intersector.overlapConvexPolygons(laser.getPolygon(), sigh2.getPolygon())) {
                    sigh2.setPosition(ran.nextInt(Gdx.graphics.getWidth() - 64), ran.nextInt(Gdx.graphics.getHeight() - 64));
                    laser.remove();
                }

                if (Intersector.overlapConvexPolygons(laser.getPolygon(), tank.getPolygon())) {
                    tank.dan = 1;
                    laser.remove();
                }
            }

            if (Gdx.input.isTouched()) {
                System.out.println("x = " + Gdx.input.getX() + " y = " + (Gdx.graphics.getHeight() - Gdx.input.getY()));
            }

            if (Intersector.overlapConvexPolygons(turtle.getPolygon(), rock.getPolygon())
                || Intersector.overlapConvexPolygons(turtle.getPolygon(), rock2.getPolygon())
                || Intersector.overlapConvexPolygons(turtle.getPolygon(), rock3.getPolygon())
                || Intersector.overlapConvexPolygons(turtle.getPolygon(), rock4.getPolygon())
                || Intersector.overlapConvexPolygons(turtle.getPolygon(), sigh.getPolygon())
                || Intersector.overlapConvexPolygons(turtle.getPolygon(), sigh2.getPolygon())) {
                turtle.moveBy(-3 * MathUtils.cosDeg(turtle.getRotation()), -3 * MathUtils.sinDeg(turtle.getRotation()));
                if ((45 < turtle.getRotation() && turtle.getRotation() < 90) || (-90 < turtle.getRotation() && turtle.getRotation() < -45)) {
                    turtle.moveBy(0, -1);
                } else if ((0 < turtle.getRotation() && turtle.getRotation() < 45) || (90 < turtle.getRotation() && turtle.getRotation() < 135)) {
                    turtle.moveBy(-1, 0);

                } else if ((90 < turtle.getRotation() && turtle.getRotation() < 135) || (225 < turtle.getRotation() && turtle.getRotation() < 270)) {
                    turtle.moveBy(0, 1);
                } else if ((-45 < turtle.getRotation() && turtle.getRotation() < 0) || (180 < turtle.getRotation() && turtle.getRotation() < 225)) {
                    turtle.moveBy(1, 0);
                }
                turtle.koda = false;
                turtle.speed = 0f;
            } else {
                turtle.koda = true;
            }

            if (Intersector.overlapConvexPolygons(turtle.getPolygon(), baoquan.getPolygon())) {
                an = new An(new Texture("whirlpool.png"), 5, 2);
                an.setPosition(baoquan.getX(), baoquan.getY());
                baoquan.setPosition(ran.nextInt((int) (Gdx.graphics.getWidth() - baoquan.getWidth())), ran.nextInt((int) (Gdx.graphics.getHeight() - baoquan.getHeight())));
                stage.addActor(an);
                dropSound.play();
                starfish--;
            }

            if (Intersector.overlapConvexPolygons(turtle.getPolygon(), haiquan.getPolygon())) {
                an = new An(new Texture("whirlpool.png"), 5, 2);
                an.setPosition(haiquan.getX(), haiquan.getY());
                haiquan.setPosition(ran.nextInt((int) (Gdx.graphics.getWidth() - haiquan.getWidth())), ran.nextInt((int) (Gdx.graphics.getHeight() - haiquan.getHeight())));
                stage.addActor(an);
                dropSound.play();
                starfish--;
            }

            if (Intersector.overlapConvexPolygons(turtle.getPolygon(), bao.getPolygon())) {
                an = new An(new Texture("whirlpool.png"), 5, 2);
                an.setPosition(bao.getX(), bao.getY());
                bao.setPosition(ran.nextInt((int) (Gdx.graphics.getWidth() - bao.getWidth())), ran.nextInt((int) (Gdx.graphics.getHeight() - bao.getHeight())));
                stage.addActor(an);
                dropSound.play();
                starfish--;
            }

            if (Intersector.overlapConvexPolygons(turtle.getPolygon(), nawn.getPolygon())) {
                an = new An(new Texture("whirlpool.png"), 5, 2);
                an.setPosition(nawn.getX(), nawn.getY());
                nawn.setPosition(ran.nextInt((int) (Gdx.graphics.getWidth() - nawn.getWidth())), ran.nextInt((int) (Gdx.graphics.getHeight() - nawn.getHeight())));
                stage.addActor(an);
                dropSound.play();
                starfish--;
            }

            if (Intersector.overlapConvexPolygons(turtle.getPolygon(), sao.getPolygon())) {
                an = new An(new Texture("whirlpool.png"), 5, 2);
                an.setPosition(sao.getX(), sao.getY());
                sao.setPosition(ran.nextInt((int) (Gdx.graphics.getWidth() - sao.getWidth())), ran.nextInt((int) (Gdx.graphics.getHeight() - sao.getHeight())));
                stage.addActor(an);
                dropSound.play();
                starfish--;
            }

            stage.draw();
            stage.act();
            tank.duoi(turtle.getX(), turtle.getY());
            if(tank.dan == 1 && cd > 3){
                Shark thuy = new Shark(Gdx.graphics.getWidth(),ran.nextInt(0,Gdx.graphics.getHeight()),stage);
                sharks.add(thuy);
                cd = 0;
            }

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            for(Shark thuy : sharks){
                shapeRenderer.polygon(thuy.getPolygon().getTransformedVertices());
            }
//            shapeRenderer.polygon(turtle.getPolygon().getTransformedVertices());
//            shapeRenderer.polygon(tank.getPolygon().getTransformedVertices());
            shapeRenderer.end();
        }else{
            nenMusic.stop();
            if(input.isKeyJustPressed(Input.Keys.C) && kt){
                game.setScreen(new MenuScreen(game));// goi man hinh moi
            }
        }
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.polygon(turtle.getPolygon().getTransformedVertices());
        shapeRenderer.polygon(tank.getPolygon().getTransformedVertices());
        shapeRenderer.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
