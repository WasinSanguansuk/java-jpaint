package application.shapes;

import application.Point;
import model.ShapeShadingType;

import java.awt.*;

public class ShapeTriangle extends Shape {
    // Data

    // Constructors
    public ShapeTriangle(ShapeInfo shapeInfo) {
        this.shapeInfo = shapeInfo;

        // Find topLeftPoint & bottomRightPoint
        this.topLeftPoint = new Point(0, 0);
        this.bottomRightPoint = new Point(0, 0);

        if (this.shapeInfo.getPressedPoint().getX() < this.shapeInfo.getReleasedPoint().getX()) {
            this.topLeftPoint.setX(this.shapeInfo.getPressedPoint().getX());
            this.bottomRightPoint.setX(this.shapeInfo.getReleasedPoint().getX());
        } else {
            this.topLeftPoint.setX(this.shapeInfo.getReleasedPoint().getX());
            this.bottomRightPoint.setX(this.shapeInfo.getPressedPoint().getX());
        }

        if (this.shapeInfo.getPressedPoint().getY() < this.shapeInfo.getReleasedPoint().getY()) {
            this.topLeftPoint.setY(this.shapeInfo.getPressedPoint().getY());
            this.bottomRightPoint.setY(this.shapeInfo.getReleasedPoint().getY());
        } else {
            this.topLeftPoint.setY(this.shapeInfo.getReleasedPoint().getY());
            this.bottomRightPoint.setY(this.shapeInfo.getPressedPoint().getY());
        }
    }

    // Methods
    @Override
    public void draw(Graphics2D g2D) {
        int[] xArray = new int[3];
        int[] yArray = new int[3];

        xArray[0] = this.shapeInfo.getPressedPoint().getX();
        xArray[1] = this.shapeInfo.getReleasedPoint().getX();
        xArray[2] = this.shapeInfo.getPressedPoint().getX();

        yArray[0] = this.shapeInfo.getPressedPoint().getY();
        yArray[1] = this.shapeInfo.getReleasedPoint().getY();
        yArray[2] = this.shapeInfo.getReleasedPoint().getY();

        // ShadingType
        if (this.shapeInfo.getShadingType() == ShapeShadingType.OUTLINE) {
            g2D.setStroke(new BasicStroke(5));
            g2D.setColor(this.shapeInfo.getPrimaryColor());
            g2D.drawPolygon(xArray, yArray, 3);

        } else if (this.shapeInfo.getShadingType() == ShapeShadingType.FILLED_IN) {
            g2D.setColor(this.shapeInfo.getPrimaryColor());
            g2D.fillPolygon(xArray, yArray, 3);
            //System.out.println("fillPolygon");

        } else {
            g2D.setColor(this.shapeInfo.getPrimaryColor());
            g2D.fillPolygon(xArray, yArray, 3);

            g2D.setStroke(new BasicStroke(5));
            g2D.setColor(this.shapeInfo.getSecondaryColor());
            g2D.drawPolygon(xArray, yArray, 3);
        }
    }

    @Override
    public void drawOutline(Graphics2D g2D) {
        int[] xArray = new int[3];
        int[] yArray = new int[3];

        xArray[0] = this.shapeInfo.getPressedPoint().getX();
        xArray[1] = this.shapeInfo.getReleasedPoint().getX();
        xArray[2] = this.shapeInfo.getPressedPoint().getX();

        yArray[0] = this.shapeInfo.getPressedPoint().getY();
        yArray[1] = this.shapeInfo.getReleasedPoint().getY();
        yArray[2] = this.shapeInfo.getReleasedPoint().getY();

        if (xArray[0] < xArray[1]) {
            if (yArray[0] < yArray[2]) {
                xArray[0] -= 5;
                xArray[1] += 5;
                xArray[2] -= 5;
                yArray[0] -= 5;
                yArray[1] += 5;
                yArray[2] += 5;
            } else {
                xArray[0] -= 5;
                xArray[1] += 5;
                xArray[2] -= 5;
                yArray[0] += 5;
                yArray[1] -= 5;
                yArray[2] -= 5;
            }
        } else {
            if (yArray[0] < yArray[2]) {
                xArray[0] += 5;
                xArray[1] -= 5;
                xArray[2] += 5;
                yArray[0] -= 5;
                yArray[1] += 5;
                yArray[2] += 5;
            } else {
                xArray[0] += 5;
                xArray[1] -= 5;
                xArray[2] += 5;
                yArray[0] += 5;
                yArray[1] -= 5;
                yArray[2] -= 5;
            }
        }

        g2D.drawPolygon(xArray, yArray, 3);
    }

    @Override
    public ShapeInfo getShapeInfo() {
        return this.shapeInfo;
    }

    @Override
    public Point getTopLeftPoint() {
        return this.topLeftPoint;
    }

    @Override
    public Point getBottomRightPoint() {
        return this.bottomRightPoint;
    }

    @Override
    public void translateAllPoint(int deltaX, int deltaY) {
        this.shapeInfo.getPressedPoint().setXY(this.shapeInfo.getPressedPoint().getX() + deltaX, this.shapeInfo.getPressedPoint().getY() + deltaY);
        this.shapeInfo.getReleasedPoint().setXY(this.shapeInfo.getReleasedPoint().getX() + deltaX, this.shapeInfo.getReleasedPoint().getY() + deltaY);
        this.topLeftPoint.setXY(this.topLeftPoint.getX() + deltaX, this.topLeftPoint.getY() + deltaY);
        this.bottomRightPoint.setXY(this.bottomRightPoint.getX() + deltaX, this.bottomRightPoint.getY() + deltaY);
    }
}
