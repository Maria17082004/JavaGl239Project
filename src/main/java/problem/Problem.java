package problem;

import javax.media.opengl.GL2;
import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс задачи
 */
public class Problem {
    /**
     * текст задачи
     */
    public static final String PROBLEM_TEXT = "ПОСТАНОВКА ЗАДАЧИ:\n" +
            "Заданы точки\n" +
            "Требуется найти точку, являющуюся пересечением пары прямых построенных на заданных точках,\n" +
            "лежащую на минимальном удалении от начала координат";

    public static final String PROBLEM_CAPTION = "Итоговый проект ученицы Мокеевой Марии (10-2)";

    private static final String POINTS_FILE_NAME = "points.txt";
    private static final String LINES_FILE_NAME = "lines.txt";
    private static final String INTERSECTION_FILE_NAME = "intersection.txt";

    private final ArrayList<Point> points;
    private ArrayList<Line> linesToDraw;
    private Point intersection;
    private Axes axes;

    public Problem() {
        axes = new Axes();
        points = new ArrayList<>();
        linesToDraw = new ArrayList<>();
        intersection = new Point();
    }

    public void addPoint(double x, double y) {
        points.add(new Point(x, y));
    }

    public void solve() {
        for (Point point : points)
            point.setType(PointType.UNUSED);

        ArrayList<Line> lines = new ArrayList<>();
        for (Point begin : points) {
            for (Point end : points) {
                if (!end.equals(begin)) {
                    lines.add(new Line(begin, end));
                }
            }
        }

        if (intersection.type == PointType.NONE) {
            intersection.x = 1;
            intersection.y = 1;
        }

        double minSize = 1;
        for (Line first : lines) {
            if (first.distanceFromOrigin() > intersection.size())
                continue;

            for (Line second : lines) {
                if (second.distanceFromOrigin() > intersection.size())
                    continue;

                Vec2 intersection = first.findIntersection(second);
                if (intersection.sizeSq() < minSize) {
                    minSize = intersection.sizeSq();
                    this.intersection = new Point(intersection, PointType.INTERSECTION);

                    linesToDraw = new ArrayList<>();
                    linesToDraw.add(first);
                    linesToDraw.add(second);
                }
            }
        }

        for (Line line : linesToDraw) {
            for (Point point : points) {
                if (point == line.begin || point == line.end) {
                    point.setType(PointType.PAIRED);
                }
            }
        }
    }

    public void loadFromFile() {
        // Загрузка точек
        points.clear();
        try {
            File file = new File(POINTS_FILE_NAME);
            Scanner sc = new Scanner(file);
            // пока в файле есть непрочитанные строки
            while (sc.hasNextLine()) {
                Point point = new Point(sc.nextDouble(), sc.nextDouble());
                sc.nextLine();

                point.setType(PointType.valueOf(sc.nextLine()));
                points.add(point);
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }

        // Загрузка линий
        linesToDraw.clear();
        try {
            File file = new File(LINES_FILE_NAME);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                linesToDraw.add(new Line(new Vec2(sc.nextDouble(), sc.nextDouble()), new Vec2(sc.nextDouble(), sc.nextDouble())));
                sc.nextLine();
            }
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }

        // Загрузка точки пересечения
        try {
            File file = new File(INTERSECTION_FILE_NAME);
            Scanner sc = new Scanner(file);
            intersection = new Point(new Vec2(sc.nextDouble(), sc.nextDouble()), PointType.INTERSECTION);
        } catch (NoSuchElementException ex) {
            System.out.println("Точка пересечения не была сохранена");
        } catch (Exception ex) {
            System.out.println("Ошибка чтения из файла: " + ex);
        }
    }

    public void saveToFile() {
        // Сохранение точек
        try {
            PrintWriter out = new PrintWriter(new FileWriter(POINTS_FILE_NAME));
            for (Point point : points) {
                out.printf("%.4f %.4f\n", point.x, point.y);
                out.println(point.type);
            }

            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }

        // Сохранение прямых
        try {
            PrintWriter out = new PrintWriter(new FileWriter(LINES_FILE_NAME));
            for (Line line : linesToDraw) {
                out.printf("%.4f %.4f %.4f %.4f\n", line.begin.x, line.begin.y, line.end.x, line.end.y);
            }

            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }

        // Сохранение точки пересечения
        try {
            PrintWriter out = new PrintWriter(new FileWriter(INTERSECTION_FILE_NAME));

            if (intersection.type != PointType.NONE)
                out.printf("%.4f %.4f\n", intersection.x, intersection.y);

            out.close();
        } catch (IOException ex) {
            System.out.println("Ошибка записи в файл: " + ex);
        }
    }

    public void addRandomPoints(int n) {
        for (int i = 0; i < n; i++) {
            points.add(Point.makeRandomPoint());
        }
    }

    public void clear() {
        points.clear();
        linesToDraw.clear();
        intersection.setType(PointType.NONE);
    }

    /**
     * Нарисовать задачу
     *
     * @param gl переменная OpenGL для рисования
     */
    public void render(GL2 gl) {
        axes.render(gl);

        for (Point point : points) {
            point.render(gl);
        }

        for (Line line : linesToDraw)
            line.render(gl);

        Line fromOriginToPoint = new Line(new Vec2(0,0), intersection);
        fromOriginToPoint.setColor(0.3, 0.5, 0.5);
        fromOriginToPoint.render(gl);

        intersection.render(gl);


    }
}
