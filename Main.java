// Author : Nathan Li
// ICS4U Final Project
// Baker's Dozen Solitaire
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main extends Applet implements ActionListener, MouseListener, MouseMotionListener
{
    // Place instance variables here
    Graphics g; // declares a graphics canvas for drawing
    PileClass[] arr = new PileClass [13];
    PileClass[] stack = new PileClass [4];
    PileClass startingPile = new PileClass ();
    int[] stackValue = new int [4];
    PileClass movePile = new PileClass ();
    DeckClass d1;
    int difx;
    int dify;
    int pos;
    int originalPile;
    boolean done;
    Point original;
    boolean[] OKToMove = new boolean [13];
    Vector order = new Vector (0, 1);
    BorderLayout lm = new BorderLayout ();
    int start;
    Button restartButton = new Button ("Restart");
//
    public void init ()
    {
	// Place the body of the initialization method here
	Panel pDraw = new Panel ();
	add ("Centre", pDraw);
	g = pDraw.getGraphics (); // gets canvas created by browser, replaces new statement
	setLayout (lm);
	d1 = new DeckClass ('s');
	d1.shuffle ();
	setLayout (lm);

	restart ();

	//set it = 0 so that it will do the initial animation
	start = 0;

	//set up for the panels and the layout
	Panel pde = new Panel ();
	pde.setLayout (new FlowLayout ());
	pde.add ("Restart", restartButton);
	add ("South", pde);
	setLayout (lm);
	setSize (800, 1050); //set the size of the applet
	setBackground (new Color (0, 130, 0)); //set background colour)


	addMouseListener (this);
	addMouseMotionListener (this);
	restartButton.addActionListener (this);
    } // init method

//
    public void restart ()
    {
	// initializing all the piles and the variables
	d1 = new DeckClass ('s');
	d1.shuffle ();
//
	for (int i = 0 ; i < 13 ; i++)
	{
	    arr [i] = new PileClass ();
	    arr [i].setLeftPoint (((Point) Globals.p ().get (i)).x, ((Point) Globals.p ().get (i)).y);
	}


	for (int i = 0 ; i < 4 ; i++)
	{
	    stack [i] = new PileClass ();
	    stack [i].setLeftPoint (((Point) Globals.s ().get (i)).x, ((Point) Globals.s ().get (i)).y);
	    stackValue [i] = 0;
	}

	for (int i = 0 ; i < 13 ; i++)
	{
	    OKToMove [i] = false;
	}

	// setting stuff = 0 so no weird errors
	difx = 0;
	dify = 0;
	pos = 0;
	originalPile = -1;
	original = new Point (0, 0);
	done = false;
	start = 1;
    }


    public void update (Graphics g)
    {
	// double buffering
	Graphics offgc;
	Image offscreen = null;
	Dimension d = size ();

	// create the offscreen buffer and associated Graphics
	offscreen = createImage (d.width, d.height);
	offgc = offscreen.getGraphics ();
	// clear the exposed area
	offgc.setColor (getBackground ());
	offgc.fillRect (0, 0, d.width, d.height);
	offgc.setColor (getForeground ());
	// do normal redraw
	paint (offgc);
	// transfer offscreen to window
	g.drawImage (offscreen, 0, 0, this);
    }


    public void actionPerformed (ActionEvent e)
    {
	Object objSource = e.getSource ();
 
	if (objSource == restartButton) // for restart button press
	{
	    restart ();
	    repaint ();
	}
    }


    public boolean action (Event e, Object o)
    {
	repaint ();
	return true;
    }


    public void mouseMoved (MouseEvent e)
    {

    }


    public void mouseClicked (MouseEvent e)
    {
	repaint ();
    }


    public void mouseEntered (MouseEvent e)
    {

    }


    public void mouseExited (MouseEvent e)
    {

    }



    public void mouseDragged (MouseEvent e)
    {
	// I made a check variable because I didn't want to have an extra global variable
	int check = 0;
	for (int i = 0 ; i < 13 ; i++)
	{
	    if (OKToMove [i])
	    {
		check = i; // accesses which pile the card(s) the player is attempting to move are in
	    }
	}
	if (OKToMove [check] && e.getX () < 1100 && e.getY () < 1100 && e.getX () > 0 && e.getY () > 0) // checks if the player is attempting the go out of the page
	{
	    if (movePile.getSize () != 0) // making sure they're actually dragging something
	    {
		movePile.setLeftPoint (e.getX () - difx, e.getY () - dify); // the dragging animation
	    }
	}
	else
	{
	    movePile.setLeftPoint (original.x, original.y); // if they attempt to go out of range, then just put the card back to the original position
	}
	repaint ();
    }



    public void mousePressed (MouseEvent e)
    {
	if (e.getButton () == 1) // making sure it doesn't bug if they right click
	{
	    int temp = 0; // this is the same as the check variable in the mouseDragged method
	    for (int i = 0 ; i < 13 ; i++)
	    {
		if (arr [i].isInPile (e.getX (), e.getY ()) && arr [i].getSize () != 0) // checking which pile the card(s) the player is trying to move are in
		{
		    OKToMove [i] = true;
		    temp = i;
		}
	    }


	    if (OKToMove [temp])
	    {
		pos = arr [temp].getPos (e.getX (), e.getY ()); // getting the position of the card they are trying to move in the pile
		difx = e.getX () - arr [temp].getLeftX ();
		dify = e.getY () - (arr [temp].getLeftY () + Globals.CARD_DIM.x * 3 / 7 * pos); // the difx and dify allow the subpile to be dragged from anywhere on it

		original = new Point (arr [temp].getCard (pos).getCentreX () - Globals.CARD_DIM.x / 2, arr [temp].getCard (pos).getCentreY () - Globals.CARD_DIM.y / 2); // storing the x and y of the original position
		movePile = arr [temp].getPile (pos); // the subpile that is being moved
		originalPile = temp; // storing the pile value that the subpile originated from

		if (movePile.canDrag ()) // checking if it satisfies drag conditions (each card is 1 value below the one above it)
		{
		    movePile.setLeftPoint (e.getX () + difx, e.getY () + dify); // moving it
		}
		else
		{
		    OKToMove [temp] = false;
		    movePile.setLeftPoint (original.x, original.y); // putting it back in its original place
		}
	    }
	}
    }


    public void mouseReleased (MouseEvent e)
    {
	int temp = 0;
	int choice = 0; // choice is to differentiate between if they are selecting one of the 13 play piles or one of the 4 stacks (though those are piles as well)
	for (int i = 0 ; i < 13 ; i++)
	{
	    if (arr [i].isInPile (e.getX (), e.getY ()))
	    {
		choice = 1;
		temp = i;
	    }
	}
	for (int i = 0 ; i < 4 ; i++)
	{
	    if (stack [i].isInPile (e.getX (), e.getY ()))
	    {
		choice = 2;
		temp = i;
	    }
	}
	if (choice == 1) // selecting play pile
	{
	    if (movePile.getSize () != 0) // making sure they are trying to move something
	    {
		if (arr [temp].canPlace (movePile) && movePile.canDrag ()) // if it is draggable and if it is placeable
		{
		    arr [temp].addPile (movePile); // add it to its new position
		}
		else
		{
		    arr [originalPile].addPile (movePile); // move it back to original position
		}
	    }
	}
	else if (choice == 2) // selecting stack
	{
	    boolean place = false; // variable to check if it is successfully placed
	    if (movePile.getSize () == 1) // can only put 1 card at a time on the stack
	    {
		for (int i = 0 ; i < 4 ; i++)
		{
		    if (movePile.getCard (0).getSuit () == temp + 1 && movePile.getCard (0).getValue () - stackValue [temp] == 1)
		    {
			stack [temp].addPile (movePile);
			stackValue [temp]++;
			place = true;
			break; // this adds the card and increases the value of that stack
		    }
		}
	    }
	    if (!place && movePile.getSize () > 0) // if nothing is placed, move it back
	    {
		arr [originalPile].addPile (movePile);
	    }
	}
	else
	{
	    if (movePile.getSize () != 0) // if they aren't trying to put it on anything, just move it back
	    {
		arr [originalPile].addPile (movePile);
	    }
	}

	for (int i = 0 ; i < 13 ; i++) // reset everything
	{
	    OKToMove [i] = false;
	}


	for (int i = 0 ; i < movePile.getSize () ; i++) // reset the movePile
	{
	    movePile.dealCard (0);
	}

	original = new Point (0, 0);
	originalPile = -1; // reset originals
	repaint ();
    }


    public void paint (Graphics g)
    {
	g.drawString ("By Nathan Li", 0, 10);
	// Place the body of the drawing method here
	if (start == 2) // this is the game state where they are regularly playing
	{
	    if (stackValue [0] == 13 && stackValue [1] == 13 && stackValue [2] == 13 && stackValue [3] == 13) // check if they've won
	    {
		done = true;
	    }
	    if (done)
	    {
		System.out.println ("You win!");
	    }
	    else
	    {
		for (int i = 0 ; i < 13 ; i++) // drawing the pile outlines and the 4 suits on the stacks
		{
		    g.setColor (Color.black);
		    Globals.d1.draw (g);
		    Globals.c1.draw (g);
		    Globals.h1.draw (g);
		    Globals.s1.draw (g);
		    g.drawRect (((Point) Globals.p ().get (i)).x, ((Point) Globals.p ().get (i)).y, Globals.CARD_DIM.x, Globals.CARD_DIM.y);
		    arr [i].drawPile (g, true);
		}


		for (int i = 0 ; i < 4 ; i++) // drawing the stacks
		{
		    g.setColor (Color.black);
		    g.drawRect (((Point) Globals.s ().get (i)).x, ((Point) Globals.s ().get (i)).y, Globals.CARD_DIM.x, Globals.CARD_DIM.y);
		    stack [i].drawPile (g, false);
		}

		for (int i = 0 ; i < 13 ; i++) // draw the actual piles
		{
		    arr [i].drawPile (g, true);
		}

		movePile.drawPile (g, true); // draw the pile that is being moved
	    }
	}
	else if (start == 0) // this is the state at the beginning of the game, with the dealing animations
	{
	    for (int i = 0 ; i < 13 ; i++)
	    {
		Globals.d1.draw (g);
		Globals.c1.draw (g);
		Globals.h1.draw (g);
		Globals.s1.draw (g);
	    }

	    for (int i = 0 ; i < 4 ; i++)
	    {
		g.setColor (Color.black);
		g.drawRect (((Point) Globals.s ().get (i)).x, ((Point) Globals.s ().get (i)).y, Globals.CARD_DIM.x, Globals.CARD_DIM.y);
		stack [i].drawPile (g, false);
	    }

	    for (int i = 0 ; i < 7 ; i++) // the animation where I just slide the cards to their places
	    {
		for (int j = 0 ; j < 4 ; j++)
		{
		    CardClass c1 = d1.getCard (i * 4 + j);
		    c1.setFace (1);
		    c1.setCentre (400, 1000);
		    c1.slideTo (((Point) Globals.p ().get (i)).x + Globals.CARD_DIM.x / 2, ((Point) Globals.p ().get (i)).y + Globals.CARD_DIM.x * 3 / 7 * j + Globals.CARD_DIM.y / 2, g);
		    arr [i].addCard (c1);
		    for (int k = 0 ; k < 13 ; k++)
		    {
			g.setColor (Color.black);
			arr [k].drawPile (g, true);
		    }
		}
	    }

	    for (int i = 7 ; i < 10 ; i++)
	    {
		for (int j = 0 ; j < 4 ; j++)
		{
		    CardClass c1 = d1.getCard (i * 4 + j);
		    c1.setFace (1);
		    c1.setCentre (400, 1000);
		    c1.slideTo (((Point) Globals.p ().get (i)).x + Globals.CARD_DIM.x / 2, ((Point) Globals.p ().get (i)).y + Globals.CARD_DIM.x * 3 / 7 * j + Globals.CARD_DIM.y / 2, g);
		    arr [i].addCard (c1);
		    for (int k = 0 ; k < 13 ; k++)
		    {
			g.setColor (Color.black);
			arr [k].drawPile (g, true);
		    }
		}
	    }

	    for (int i = 12 ; i >= 10 ; i--)
	    {
		for (int j = 0 ; j < 4 ; j++)
		{
		    CardClass c1 = d1.getCard (i * 4 + j);
		    c1.setFace (1);
		    c1.setCentre (400, 1000);
		    c1.slideTo (((Point) Globals.p ().get (i)).x + Globals.CARD_DIM.x / 2, ((Point) Globals.p ().get (i)).y + Globals.CARD_DIM.x * 3 / 7 * j + Globals.CARD_DIM.y / 2, g);
		    arr [i].addCard (c1);
		    for (int k = 0 ; k < 13 ; k++)
		    {
			g.setColor (Color.black);
			arr [k].drawPile (g, true);
		    }
		}
	    }


	    for (int i = 0 ; i < 4 ; i++)
	    {
		g.setColor (Color.black);
		g.drawRect (((Point) Globals.s ().get (i)).x, ((Point) Globals.s ().get (i)).y, Globals.CARD_DIM.x, Globals.CARD_DIM.y);
		stack [i].drawPile (g, false);
	    }

	    movePile.drawPile (g, true); // draw the pile that is being moved

	    d1.delay (1000); // the game has to have the kings on the bottom, so after a delay, I sort the piles so that the kings appear at the bottom
	    for (int i = 0 ; i < 13 ; i++)
	    {
		PileClass tempPile = new PileClass ();
		tempPile = arr [i].kingSort ();
		arr [i] = tempPile;
		arr [i].drawPile (g, true);
	    }
	    start = 2;
	}
	else if (start == 1) // this is the state where it restarts without animation
	{
	    Color col = new Color (0, 130, 0);
	    g.setColor (col);
	    g.fillRect (0, 0, 800, 1050);
	    for (int i = 0 ; i < 13 ; i++)
	    {
		Globals.d1.draw (g);
		Globals.c1.draw (g);
		Globals.h1.draw (g);
		Globals.s1.draw (g);
	    }


	    for (int i = 0 ; i < 4 ; i++)
	    {
		g.setColor (Color.black);
		g.drawRect (((Point) Globals.s ().get (i)).x, ((Point) Globals.s ().get (i)).y, Globals.CARD_DIM.x, Globals.CARD_DIM.y);
		stack [i].drawPile (g, false);
	    }


	    for (int i = 0 ; i < 13 ; i++)
	    {
		for (int j = 0 ; j < 4 ; j++)
		{
		    CardClass c1 = d1.getCard (i * 4 + j);
		    c1.setFace (1);
		    c1.setCentre (((Point) Globals.p ().get (i)).x + Globals.CARD_DIM.x / 2, ((Point) Globals.p ().get (i)).y + Globals.CARD_DIM.x * 3 / 7 * j + Globals.CARD_DIM.y / 2);
		    arr [i].addCard (c1);
		}
	    }


	    movePile.drawPile (g, true); // draw the pile that is being moved

	    for (int i = 0 ; i < 13 ; i++)
	    {
		PileClass tempPile = new PileClass ();
		tempPile = arr [i].kingSort ();
		arr [i] = tempPile;
		arr [i].drawPile (g, true);
	    }
	    start = 2;
	}
    } // paint method
}


