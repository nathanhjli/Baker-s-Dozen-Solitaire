// Author : Nathan Li
// ICS4U Final Project
// Baker's Dozen Solitaire
import java.awt.*;
import hsa.Console;

public abstract class ShapeClass
{
    private int iHeight;
    private int iWidth;
    private int iCentreX;
    private int iCentreY;
    private Color cColor;

    public ShapeClass ()
    {
	iHeight = 100;
	iWidth = 80;
	iCentreX = 100;
	iCentreY = 100;
	cColor = Color.red;
    }


    public void setColor (Color color)
    {
	cColor = color;
    }


    public void setHeight (int ipHeight)
    {
	iHeight = ipHeight;
    }


    public void setWidth (int ipWidth)
    {
	iWidth = ipWidth;
    }


    public void setCentre (int ipCentreX, int ipCentreY)
    {
	iCentreX = ipCentreX;
	iCentreY = ipCentreY;
    }


    public void setCentreX (int ipCentreX)
    {
	iCentreX = ipCentreX;
    }


    public void setCentreY (int ipCentreY)
    {
	iCentreY = ipCentreY;
    }


    public int getHeight ()
    {
	return iHeight;
    }


    public int getWidth ()
    {
	return iWidth;
    }


    public Color getColor ()
    {
	return cColor;
    }


    public void erase (Graphics g)
    {
	Color cOldColor = getColor ();
	setColor (Color.white);
	draw (g);
	setColor (cOldColor);
    }


    public int getCentreX ()
    {
	return iCentreX;
    }


    public int getCentreY ()
    {
	return iCentreY;
    }


    public abstract void draw (Graphics g);

    public void delay (int iDelayTime)
    {
	long lFinalTime = System.currentTimeMillis () + iDelayTime;
	do
	{
	}
	while (lFinalTime >= System.currentTimeMillis ());
    }
}

