package problem;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import java.util.Random;
import problem.PointType.*;

public class Point extends Vec2 {
    PointType type;

    Point() {
        x = 0;
        y = 0;
        type = PointType.NONE;
    }

    Point(double x, double y) {
        this.x = x;
        this.y = y;
        this.type = PointType.UNUSED;
    }

    Point(Vec2 position, PointType type) {
        this.x = position.x;
        this.y = position.y;
        this.type = type;
    }

    void setType(PointType type) {
        this.type = type;
    }

    static Point makeRandomPoint() {
        Random r = new Random();
        return new Point(new Vec2(r.nextDouble() * 2 - 1, r.nextDouble() * 2 - 1), PointType.UNUSED);
    }

    void render(GL2 gl) {
        switch(type) {
            case UNUSED:
                gl.glColor3d(0.5, 0.5, 0.5);
                gl.glPointSize(2);
                break;
            case PAIRED:
                gl.glColor3d(0.3, 1.0, 0.3);
                gl.glPointSize(3);
                break;
            case INTERSECTION:
                gl.glColor3d(1.0, 0.3, 0.0);
                gl.glPointSize(4);
                break;

            case NONE:
                return;

            default:
                /* Impossible to get here, maybe should raise error, todo */
                return;
        }

        gl.glBegin(GL.GL_POINTS);
        gl.glVertex2d(x, y);
        gl.glEnd();
        gl.glPointSize(1);
    }
}
