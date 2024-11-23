package Game;

import Texture.TextureReader;
import Texture.AnimListener;
import java.awt.event.*;
import java.io.IOException;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import java.util.ArrayList;

public class AnimGLEventListener extends AnimListener implements MouseListener, MouseMotionListener {
    ArrayList<Letter> letters = new ArrayList<>();
    int maxWidth = 100;
    int maxHeight = 100;
    double mouseX;
    double mouseY;

    public AnimGLEventListener() {
    }

    String[] textureNames = {
            "Man1.png", "Man2.png", "Man3.png", "Man4.png",
            "a.png", "b.png", "c.png", "d.png", "e.png", "f.png", "g.png", "h.png", "i.png", "j.png", "k.png", "l.png", "m.png", "n.png", "o.png", "p.png", "q.png", "r.png", "s.png", "t.png", "u.png", "v.png", "w.png", "x.png", "y.png", "z.png",
            "0.png", "1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png", "8.png", "9.png", "..png",
            "HealthB.png", "Health.png",
            "Back.png",
            "NinjaStar.png"
    };
    TextureReader.Texture[] texture = new TextureReader.Texture[textureNames.length];
    public int[] textures = new int[textureNames.length];

    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glGenTextures(textureNames.length, textures, 0);

        for (int i = 0; i < textureNames.length; i++) {
            try {
                texture[i] = TextureReader.readTexture(assetsFolderName + "//" + textureNames[i], true);
                gl.glBindTexture(GL.GL_TEXTURE_2D, textures[i]);
                new GLU().gluBuild2DMipmaps(
                        GL.GL_TEXTURE_2D,
                        GL.GL_RGBA,
                        texture[i].getWidth(),
                        texture[i].getHeight(),
                        GL.GL_RGBA,
                        GL.GL_UNSIGNED_BYTE,
                        texture[i].getPixels()
                );
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        letters.add(new Letter(0 , 90 ,8 , 1 , 1));
        letters.add(new Letter(20 , 90 ,15 , 1, 1));
        f();
    }

    @Override
    public void display(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        DrawBackground(gl);
        //System.out.println("run");
       for (Letter L:letters) {
            L.drawSprite(gl , textures);
        }
    }
    public void f(){
        letters.get(1).setTextureIndex(20);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    public void DrawSprite(GL gl, float x, float y, int index, float scaleX, float scaleY) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[index]);

        gl.glPushMatrix();
        gl.glTranslated(x / (maxWidth / 2.0) - 0.9, y / (maxHeight / 2.0) - 0.9, 0);
        gl.glScaled(0.1 * scaleX, 0.1 * scaleY, 1);
        gl.glBegin(GL.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);
    }
    public void DrawBackground(GL gl) {
        gl.glEnable(GL.GL_BLEND);
        gl.glBindTexture(GL.GL_TEXTURE_2D, textures[textures.length - 2]);

        gl.glPushMatrix();
        gl.glBegin(GL.GL_QUADS);

        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();
        gl.glPopMatrix();

        gl.glDisable(GL.GL_BLEND);

    }
    @Override
    public void mouseDragged(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
        mouseX = (int) convertX(e.getX(), e.getComponent().getWidth(), 0, 100);
        mouseY = (int) convertY(e.getY(), e.getComponent().getHeight(), 0, 100);
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    public double convertX(double x, double screenWidth, double left, double right) {
        return left + (x / screenWidth) * (right - left);
    }
    public double convertY(double y, double screenHeight, double bottom, double top) {
        return top - (y / screenHeight) * (top - bottom);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}