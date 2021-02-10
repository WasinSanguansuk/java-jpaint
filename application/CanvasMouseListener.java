package application;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import application.commands.CmdCreateShape;
import application.commands.ICommand;
import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

public class CanvasMouseListener implements MouseListener {
    // Data
    private PaintCanvasBase paintCanvas;
    private IApplicationState appState;
    private Point pointPressed;
    private Point pointReleased;

    // Constructors
    public CanvasMouseListener(PaintCanvasBase _paintCanvas, IApplicationState _appState) {
        this.paintCanvas = _paintCanvas;
        this.appState = _appState;
        this.pointPressed = new Point(0,0);
        this.pointReleased = new Point(0,0);
    }

    // Methods
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked: " + e.getX() + ", " + e.getY());

        // Do something here for "Select"
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("--------------------------------------------------");
        System.out.println("mousePressed: " + e.getX() + ", " + e.getY());

        this.pointPressed.setX(e.getX());
        this.pointPressed.setY(e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased: " + e.getX() + ", " + e.getY());

        this.pointReleased.setX(e.getX());
        this.pointReleased.setY(e.getY());

        if ((this.pointPressed.getX() != this.pointReleased.getX()) || (this.pointPressed.getY() != this.pointReleased.getY()))
        {
            ICommand cmd = new CmdCreateShape(this.paintCanvas, this.appState, this.pointPressed, this.pointReleased);
            cmd.execute();
        }

        this.pointPressed.setX(0);
        this.pointPressed.setY(0);
        this.pointReleased.setX(0);
        this.pointReleased.setY(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Nothing
    }
}
