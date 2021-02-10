package application.shapes;

import application.Point;
import model.ShapeShadingType;
import view.interfaces.PaintCanvasBase;

import java.awt.BasicStroke;

public class ShapeRectangle extends Shape {
    // Data

    // Constructors
    public ShapeRectangle(ShapeInfo _shapeInfo) {
        this.paintCanvas = _shapeInfo.getPaintCanvas();
        this.graphics2D = _shapeInfo.getPaintCanvas().getGraphics2D();
        this.shapeType = _shapeInfo.getShapeType();
        this.primaryColor = _shapeInfo.getPrimaryColor();
        this.secondaryColor = _shapeInfo.getSecondaryColor();
        this.shadingType = _shapeInfo.getShadingType();

        this.pressedPoint = _shapeInfo.getPressedPoint();
        this.releasedPoint = _shapeInfo.getReleasedPoint();
    }

    // Methods
    @Override
    public void draw() {
        Point topLeftPoint = new Point(0, 0);

        if (this.pressedPoint.getX() < this.releasedPoint.getX()) {
            topLeftPoint.setX(this.pressedPoint.getX());
        } else {
            topLeftPoint.setX(this.releasedPoint.getX());
        }

        if (this.pressedPoint.getY() < this.releasedPoint.getY()) {
            topLeftPoint.setY(this.pressedPoint.getY());
        } else {
            topLeftPoint.setY(this.releasedPoint.getY());
        }

        int width = Math.abs(this.releasedPoint.getX() - this.pressedPoint.getX());
        int height = Math.abs(this.releasedPoint.getY() - this.pressedPoint.getY());

        // ShadingType
        if (this.shadingType == ShapeShadingType.OUTLINE) {
            this.graphics2D.setStroke(new BasicStroke(5));
            this.graphics2D.setColor(this.primaryColor);
            this.graphics2D.drawRect(topLeftPoint.getX(), topLeftPoint.getY(), width, height);

        } else if (this.shadingType == ShapeShadingType.FILLED_IN) {
            this.graphics2D.setColor(this.primaryColor);
            this.graphics2D.fillRect(topLeftPoint.getX(), topLeftPoint.getY(), width, height);
            System.out.println("fillRect");

        } else {
            this.graphics2D.setColor(this.primaryColor);
            this.graphics2D.fillRect(topLeftPoint.getX(), topLeftPoint.getY(), width, height);

            this.graphics2D.setStroke(new BasicStroke(5));
            this.graphics2D.setColor(this.secondaryColor);
            this.graphics2D.drawRect(topLeftPoint.getX(), topLeftPoint.getY(), width, height);
        }

//        // Only Green Rectangle for now
//        Graphics2D graphics2D = this.paintCanvas.getGraphics2D();
//        graphics2D.setColor(Color.GREEN);
//        graphics2D.fillRect(this.topLeftPoint.getX(), this.topLeftPoint.getY(), this.width, this.height);

        //--------------------------------------------------
        // Testing

        //graphics2D.fillPolygon();

        //--------------------------------------------------
    }

    @Override
    public PaintCanvasBase getPaintCanvas() {
        return this.paintCanvas;
    }
}
