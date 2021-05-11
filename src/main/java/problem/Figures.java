package problem;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
public class Figures {
    public static void renderPoint(GL2 gl, Vec2 pos, float size) {
        gl.glPointSize(size);
        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(pos.x, pos.y);
        gl.glEnd();
    }
    public static void renderLine(GL2 gl, Vec2 posbegin,Vec2 posend) {
        gl.glLineWidth(6);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3d(1.0, 0.5, 0.0);
        gl.glVertex2d(posbegin.x, posbegin.y);
        gl.glColor3d(0.5, 0.0, 1.0);
        gl.glVertex2d(posend.x, posend.y);
        gl.glEnd();

    }
    public static void renderTriangle(GL2 gl, Vec2 pos1, Vec2 pos2, Vec2 pos3, boolean filled) {
        if (filled) {
            gl.glLineWidth(7);
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex2d(pos1.x, pos1.y);
            gl.glVertex2d(pos2.x, pos2.y);
            gl.glVertex2d(pos3.x, pos3.y);
            gl.glEnd();

        } else {
            gl.glLineWidth(7);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(pos1.x, pos1.y);
            gl.glVertex2d(pos2.x, pos2.y);
            gl.glEnd();
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(pos3.x, pos3.y);
            gl.glVertex2d(pos2.x, pos2.y);
            gl.glEnd();
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(pos1.x, pos1.y);
            gl.glVertex2d(pos3.x, pos3.y);
            gl.glEnd();

        }
    }
    public static void renderSquare(GL2 gl, Vec2 pos1, Vec2 pos2, Vec2 pos3, Vec2 pos4, boolean filled) {
        if (filled) {
            gl.glLineWidth(7);
            gl.glBegin(GL.GL_TRIANGLES);
            gl.glVertex2d(pos1.x, pos1.y);
            gl.glVertex2d(pos2.x, pos2.y);
            gl.glVertex2d(pos3.x, pos3.y);
            gl.glVertex2d(pos4.x, pos4.y);
            gl.glEnd();
        } else {
            gl.glLineWidth(7);
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(pos1.x, pos1.y);
            gl.glVertex2d(pos2.x, pos2.y);
            gl.glEnd();
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(pos2.x, pos2.y);
            gl.glVertex2d(pos3.x, pos3.y);
            gl.glEnd();
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(pos3.x, pos3.y);
            gl.glVertex2d(pos4.x, pos4.y);
            gl.glEnd();
            gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(pos4.x, pos4.y);
            gl.glVertex2d(pos1.x, pos1.y);
            gl.glEnd();

        }
    }
    public static void renderCircle(GL2 gl, Vec2 pos, float size) {
        gl.glColor3f(0.0f,1.0f,0.0f);
        gl.glBegin(GL.GL_LINE_LOOP);
        float a;
        for(int i = 0; i < 50; i++) {
            a = (float)i / 50.0f * 3.1415f * 2.0f;
            gl.glVertex2f(-0.5f + (float)Math.cos(a) * 0.5f, 0 + (float)Math.sin(a) * 0.5f);
        }
        gl.glEnd();
    }
}