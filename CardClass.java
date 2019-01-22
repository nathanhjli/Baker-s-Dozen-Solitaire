// Author : Nathan Li
// ICS4U Final Project
// Baker's Dozen Solitaire
import java.awt.*;
import hsa.Console;

class CardClass extends ShapeClass
{
    private String fvalue;
    private int suit;
    private boolean face;

    public CardClass ()
    {
	super.setWidth (70);
	super.setHeight (100);
	fvalue = "A";
	suit = 1;
	face = true;
    }


    public CardClass (int f, int su, boolean fa, int centrex, int centrey)
    {
	super.setWidth (70);
	super.setHeight (100);
	setValue (f);
	setSuit (su);
	face = fa;
	super.setCentre (centrex, centrey);
    }


    public void draw (Graphics g)
    {
	if (face == true)
	{
	    g.setColor (Color.white);
	    g.fillRect (getCentreX () - getWidth () / 2, getCentreY () - getHeight () / 2, getWidth (), getHeight ());
	    g.setColor (Color.black);
	    g.drawRect (getCentreX () - getWidth () / 2, getCentreY () - getHeight () / 2, getWidth (), getHeight ());

	    if (suit == 1)
	    {
		DiamondClass d1 = new DiamondClass ();
		d1.setHeight (getHeight () / 4);
		d1.setCentre (getCentreX (), getCentreY () - getHeight () / 16);
		d1.draw (g);
		DiamondClass d2 = new DiamondClass ();
		d2.setHeight (getHeight () / 8);
		d2.setCentre (getCentreX () - getWidth () / 2 + d2.getWidth () + 2, getCentreY () - getHeight () / 4);
		d2.draw (g);
	    }
	    else if (suit == 2)
	    {
		ClubClass c1 = new ClubClass ();
		c1.setHeight (getHeight () / 4);
		c1.setCentre (getCentreX (), getCentreY () - getHeight () / 16);
		c1.draw (g);
		ClubClass c2 = new ClubClass ();
		c2.setHeight (getHeight () / 8);
		c2.setCentre (getCentreX () - getWidth () / 2 + c2.getWidth () + 2, getCentreY () - getHeight () / 4);
		c2.draw (g);
	    }
	    else if (suit == 3)
	    {
		HeartClass h1 = new HeartClass ();
		h1.setHeight (getHeight () / 4);
		h1.setCentre (getCentreX (), getCentreY () - getHeight () / 16);
		h1.draw (g);
		HeartClass h2 = new HeartClass ();
		h2.setHeight (getHeight () / 8);
		h2.setCentre (getCentreX () - getWidth () / 2 + h2.getWidth () + 2, getCentreY () - getHeight () / 4);
		h2.draw (g);
	    }
	    else if (suit == 4)
	    {
		SpadeClass s1 = new SpadeClass ();
		s1.setHeight (getHeight () / 4);
		s1.setCentre (getCentreX (), getCentreY () - getHeight () / 16);
		s1.draw (g);
		SpadeClass s2 = new SpadeClass ();
		s2.setHeight (getHeight () / 8);
		s2.setCentre (getCentreX () - getWidth () / 2 + s2.getWidth () + 2, getCentreY () - getHeight () / 4);
		s2.draw (g);
	    }

	    Font f1 = new Font ("SanSerif", Font.PLAIN, getHeight () / 5);
	    g.setFont (f1);
	    g.drawString (fvalue, getCentreX () - getWidth () / 2 + getWidth () / 16, getCentreY () - getHeight () / 2 + getHeight () / 5);
	}
	else
	{
	    g.setColor (Color.black);
	    g.fillRect (getCentreX () - getWidth () / 2, getCentreY () - getHeight () / 2, getWidth (), getHeight ());
	}
    }


    public void setSuit (int iSuit)
    {
	suit = iSuit;
    }


    public void setValue (int iValue)
    {
	if (iValue <= 10 && iValue > 1)
	{
	    fvalue = Integer.toString (iValue);
	}


	else if (iValue == 1)
	{
	    fvalue = "A";
	}


	else if (iValue == 11)
	{
	    fvalue = "J";
	}


	else if (iValue == 12)
	{
	    fvalue = "Q";
	}


	else if (iValue == 13)
	{
	    fvalue = "K";
	}


	else
	{
	    fvalue = "K";
	}
    }


    public void setFace (int iValue)
    {
	if (iValue == 1)
	{
	    face = true;
	}


	else
	{
	    face = false;
	}
    }


    public void erase (Graphics g)
    {
	Color colorbg = new Color (0, 130, 0);
	Color cOldColor = getColor ();
	g.setColor (colorbg);
	g.fillRect (getCentreX () - getWidth () / 2, getCentreY () - getHeight () / 2, getWidth () + 1, getHeight () + 1);
	g.setColor (cOldColor);
    }


    public int getValue ()
    {

	if (fvalue.equals ("J"))
	{
	    return 11;
	}


	else if (fvalue.equals ("Q"))
	{
	    return 12;
	}


	else if (fvalue.equals ("K"))
	{
	    return 13;
	}


	else if (fvalue.equals ("A"))
	{
	    return 1;
	}


	else
	{
	    return Integer.parseInt (fvalue);
	}
    }


    public int getSuit ()
    {
	return suit;
    }


    public int getFace ()
    {
	if (face)
	{
	    return 1;
	}
	else
	{
	    return 2;
	}
    }


    public boolean isPointInside (int x, int y)
    {
	if (x < getCentreX () + getWidth () / 2 && x > getCentreX () - getWidth () / 2 && y < getCentreY () + getHeight () / 2 && y > getCentreY () - getHeight () / 2)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public void slideTo (int x, int y, Graphics g)
    {
	int endX = x;
	int endY = y;
	int iStepSizeX, iStepSizeY;
	Color col = new Color (0, 130, 0);

	iStepSizeX = (x - getCentreX ()) / 10;
	iStepSizeY = (y - getCentreY ()) / 10;

	int oldX = getCentreX ();
	int oldY = getCentreY ();

	if (getCentreX () > endX)
	{
	    if (getCentreY () > endY)
	    {
		while (true)
		{
		    g.setColor (col);
		    g.fillRect (oldX, oldY, oldX + getWidth (), oldY + getHeight ());

		    if (getCentreX () <= endX && getCentreY () <= endY)
		    {
			setCentreX (x);
			setCentreY (y);
			draw (g);
			break;
		    }

		    if (getColor () != col)
		    {
			g.setColor (col);
			g.fillRect (oldX, oldY, oldX + getWidth (), oldY + getHeight ());
		    }

		    draw (g);

		    delay (10);

		    setColor (col);
		    erase (g);
		    setColor (Color.white);

		    if (getCentreX () > endX)
		    {
			setCentreX (getCentreX () + iStepSizeX);
		    }

		    if (getCentreY () > endY)
		    {
			setCentreY (getCentreY () + iStepSizeY);
		    }
		}
	    }

	    else if (getCentreY () < endY)
	    {
		while (true)
		{
		    g.setColor (col);
		    g.fillRect (oldX, oldY, oldX + getWidth (), oldY + getHeight ());

		    if (getCentreX () <= endX && getCentreY () <= endY)
		    {
			setCentreX (x);
			setCentreY (y);
			draw (g);
			break;
		    }

		    if (getColor () != col)
		    {
			g.setColor (col);
			g.fillRect (oldX, oldY, oldX + getWidth (), oldY + getHeight ());
		    }

		    draw (g);

		    delay (10);

		    setColor (col);
		    erase (g);
		    setColor (Color.white);

		    if (getCentreX () > endX)
		    {
			setCentreX (getCentreX () + iStepSizeX);
		    }

		    if (getCentreY () < endY)
		    {
			setCentreY (getCentreY () + iStepSizeY);
		    }
		}
	    }
	}

	else
	{
	    if (getCentreY () > endY)
	    {
		while (true)
		{
		    g.setColor (col);
		    g.fillRect (oldX, oldY, oldX + getWidth (), oldY + getHeight ());

		    if (getCentreX () <= endX && getCentreY () <= endY)
		    {
			setCentreX (x);
			setCentreY (y);
			draw (g);
			break;
		    }

		    if (getColor () != col)
		    {
			g.setColor (col);
			g.fillRect (oldX, oldY, oldX + getWidth (), oldY + getHeight ());
		    }

		    draw (g);

		    delay (10);

		    setColor (col);
		    erase (g);
		    setColor (Color.white);

		    if (getCentreX () < endX)
		    {
			setCentreX (getCentreX () + iStepSizeX);
		    }

		    if (getCentreY () > endY)
		    {
			setCentreY (getCentreY () + iStepSizeY);
		    }
		}
	    }

	    else if (getCentreY () < endY)
	    {
		while (true)
		{
		    g.setColor (col);
		    g.fillRect (oldX, oldY, oldX + getWidth (), oldY + getHeight ());

		    if (getCentreX () <= endX && getCentreY () <= endY)
		    {
			setCentreX (x);
			setCentreY (y);
			draw (g);
			break;
		    }

		    if (getColor () != col)
		    {
			g.setColor (col);
			g.fillRect (oldX, oldY, oldX + getWidth (), oldY + getHeight ());
		    }

		    draw (g);

		    delay (10);

		    setColor (col);
		    erase (g);
		    setColor (Color.white);

		    if (getCentreX () < endX)
		    {
			setCentreX (getCentreX () + iStepSizeX);
		    }

		    if (getCentreY () < endY)
		    {
			setCentreY (getCentreY () + iStepSizeY);
		    }
		}
	    }
	}
    }
}
