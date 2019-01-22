// Author : Nathan Li
// ICS4U Final Project
// Baker's Dozen Solitaire
import java.awt.*;
import hsa.Console;

public abstract class SuitClass extends ShapeClass
{
    public SuitClass ()
    {
	super.setWidth (80);
	super.setHeight (100);
    }


    public void setSize (int size)
    {
	super.setHeight (size);
	super.setWidth (size / 5 * 4);
    }


    public void setWidth (int ipWidth)
    {
	setSize (ipWidth);
    }


    public void setHeight (int ipHeight)
    {
	setSize (ipHeight);
    }
}
