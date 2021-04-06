package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public class Axes {
    private double step = 0.03;

    void setStep(double step) {

    }

    void render(GL2 gl) {
        gl.glColor3d(0.8, 0.6, 0.2);

        gl.glBegin(GL.GL_LINES);
        for (double pos = -1 - step / 3; pos <= 1; pos += step) {
            gl.glVertex2d(0, pos);
            gl.glVertex2d(0, pos + step / 3);

            gl.glVertex2d(pos, 0);
            gl.glVertex2d(pos + step / 3, 0);
        }

        gl.glEnd();
    }
}
