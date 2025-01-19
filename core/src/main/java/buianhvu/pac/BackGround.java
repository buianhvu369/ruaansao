package buianhvu.pac;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class BackGround extends Actor {
    TextureRegion region;
    Texture anh;
    BackGround(Stage s) {
        region = new TextureRegion(new Texture("water-border.jpg")) ;
        setSize(Gdx.graphics.getWidth()*3, Gdx.graphics.getHeight()*3);
        s.addActor(this);
    }
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),1,1,getRotation());
     }
}
