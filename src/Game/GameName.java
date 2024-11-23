package Game;

import Game.AnimGLEventListener;
import com.sun.opengl.util.*;
import javax.media.opengl.*;
import javax.swing.*;
import java.awt.*;

public class GameName extends JFrame {

    public static void main(String[] args) {

        new GameName();
    }
    public GameName() {
        AnimGLEventListener listener = new AnimGLEventListener();
        GLCanvas glcanvas = new GLCanvas();
        glcanvas.addGLEventListener(listener);
        glcanvas.addMouseListener(listener);
        glcanvas.addMouseMotionListener(listener);
        glcanvas.addKeyListener(listener);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        Animator animator = new FPSAnimator(glcanvas, 60);
        animator.start();
        configureWindow();
        glcanvas.requestFocus();
    }

    private void configureWindow() {
        setTitle("GameName");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
    }
}
