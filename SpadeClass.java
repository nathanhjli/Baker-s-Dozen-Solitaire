// Author : Nathan Li
// ICS4U Final Project
// Baker's Dozen Solitaire
import java.awt.*;

public class SpadeClass extends SuitClass
{
    public SpadeClass ()
    {
	setColor (Color.black);
    }


    public SpadeClass (int iNewWidth, int iNewHeight, int iNewCentreX, int iNewCentreY, Color cNewColor)
    {
	setWidth (iNewWidth);
	setHeight (iNewHeight);
	setCentre (iNewCentreX, iNewCentreY);
	setColor (cNewColor);
    }


    public void draw (Graphics c)
    {
	int iPointsX[] = new int [5];
	int iPointsY[] = new int [5];

	iPointsX [0] = getCentreX() - getWidth() / 2;
	iPointsY [0] = getCentreY();
	iPointsX [1] = getCentreX() + getWidth() / 2;
	iPointsY [1] = getCentreY();
	iPointsX [2] = getCentreX();
	iPointsY [2] = getCentreY() - getHeight() / 2;
	iPointsX [3] = getCentreX() - getWidth() / 2;
	iPointsY [3] = getCentreY() - getHeight() / 4;
	iPointsX [4] = getCentreX();
	iPointsY [4] = getCentreY() - getHeight() / 4;

	int triPointsX[] = new int [3];
	int triPointsY[] = new int [3];

	triPointsX [0] = getCentreX();
	triPointsY [0] = getCentreY();
	triPointsX [1] = getCentreX() - getWidth() / 8;
	triPointsY [1] = getCentreY() + getHeight() / 2;
	triPointsX [2] = getCentreX() + getWidth() / 8;
	triPointsY [2] = getCentreY() + getHeight() / 2;

	c.setColor (getColor());
	c.fillArc (iPointsX [3], iPointsY [3], getWidth() / 2, getHeight() / 2, 180, 180);
	c.fillArc (iPointsX [4], iPointsY [4], getWidth() / 2, getHeight() / 2, 180, 180);
	c.fillPolygon (iPointsX, iPointsY, 3);
	c.fillPolygon (triPointsX, triPointsY, 3);
    }
}
