package mpengine;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class EngineMath {

    public static double distance(Point point, double x, double y) {
        return distance(x, y, point);
    }

    public static double distance(double x, double y, Point point) {
        return distance(x, y, point.x, point.y);
    }

    public static double distance(Point point1, Point point2) {
        return distance(point1.x, point1.y, point2.x, point2.y);
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.hypot(x1 - x2, y1 - y2);
    }

    public static double angle(Point point, double x, double y) {
        return angle(x, y, point);
    }

    public static double angle(double x, double y, Point point) {
        return angle(x, y, point.x, point.y);
    }

    public static double angle(Point point1, Point point2) {
        return angle(point1.x, point1.y, point2.x, point2.y);
    }

    public static double angle(double x1, double y1, double x2, double y2) {
        return Math.atan2(x1 - x2, y1 - y2);
    }

    public static boolean collide(Point point, Rectangle2D rect) {
        return collide(point.x, point.y, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

    public static boolean collide(Point point, Point rectPoint, double rectW, double rectH) {
        return collide(point.x, point.y, rectPoint.x, rectPoint.y, rectW, rectH);
    }

    public static boolean collide(Point point, Point2D.Double rectPoint, double rectW, double rectH) {
        return collide(point.x, point.y, rectPoint.x, rectPoint.y, rectW, rectH);
    }

    public static boolean collide(Point point, double rectX, double rectY, double rectW, double rectH) {
        return collide(point.x, point.y, rectX, rectY, rectW, rectH);
    }

    public static boolean collide(double x, double y, double rectX, double rectY, double rectW, double rectH) {
        return x > rectX && y > rectY && x < rectX + rectW && y < rectY + rectH;
    }
}
