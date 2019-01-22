// Author : Nathan Li
// ICS4U Final Project
// Baker's Dozen Solitaire
import java.awt.*;

public class DiamondClass extends SuitClass
{
    public DiamondClass ()
    {
	setColor (Color.red);
    }


    public DiamondClass (int iNewWidth, int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor)
    {
	setWidth (iNewWidth);
	setHeight (iNewHeight);
	setCentre (iNewCentreX, iNewCentreY);
	setColor (cNewColor);
    }


    public void draw (Graphics g)
    {
	int[] x = new int [4];
	int[] y = new int [4];
	x [0] = getCentreX () - getWidth () / 2;
	y [0] = getCentreY ();
	x [1] = getCentreX ();
	y [1] = getCentreY () + getHeight () / 2;
	x [2] = getCentreX () + getWidth () / 2;
	y [2] = getCentreY ();
	x [3] = getCentreX ();
	y [3] = getCentreY () - getHeight () / 2;
	g.setColor (getColor());
	g.fillPolygon (x, y, 4);
    }
}
