// Author : Nathan Li
// ICS4U Final Project
// Baker's Dozen Solitaire

import java.awt.*;
import java.util.*;

// A pile is very different from a deck since only addCard dealCard and getCard are shared among them
// A pile is not a shape and cannot be delayed or erased, so it should not extend deck
public class PileClass
{
    private Vector deck = new Vector (0, 1);
    private int lx, ly;

    public PileClass ()
    {
	// pile is default empty so nothing in the constructor
    }


    public void setLeftPoint (int x, int y)  // settomg the piles
    {
	lx = x;
	ly = y;
    }


    public int getLeftX ()  // gets
    {
	return lx;
    }


    public int getLeftY ()
    {
	return ly;
    }


    public boolean isEmpty ()
    {
	return deck.isEmpty ();
    }


    public void addCard (CardClass cardToAdd)  // adding card
    {
	deck.addElement (cardToAdd);
    }


    public CardClass getCard (int Pos)  // getting the card at a position
    {
	return (CardClass) deck.get (Pos);
    }


    public int getSize ()  // gets number of cards in a pile
    {
	return deck.size ();
    }


    public CardClass dealCard (int Pos)
    {
	return (CardClass) deck.remove (Pos);   // must type cast element
    }


    public PileClass getPile (int pos)  // results the part of the pile that the user selects
    {
	int size = deck.size ();
	PileClass p = new PileClass ();
	for (int i = pos ; i < size ; i++)
	{
	    p.addCard (dealCard (pos));
	}
	return p;
    }


    public boolean canDrag ()
    {
	boolean drag = true;
	for (int i = 0 ; i < deck.size () ; i++)
	{
	    if (i == deck.size () - 1)
	    {
		break; //just making sure it doesn't go out of bounds and for the case that they are dragging the last card only
	    }
	    else if (((CardClass) deck.get (i)).getValue () - ((CardClass) deck.get (i + 1)).getValue () != 1)
	    {
		return false; //for making sure that they are trying to drag valid piles
	    }
	}
	return true;
    }


    public boolean canPlace (PileClass p)
    {
	if (deck.size () != 0)
	    if (((CardClass) deck.get (deck.size () - 1)).getValue () - p.getCard (0).getValue () == 1)
	    {
		return true;
	    }
	    else
	    {
		return false;
	    }
	else
	{
	    {
		return false;
	    }
	}
    }


    public int getPos (int x, int y)
    {
	int pos = 0;
	boolean isFound = false;

	for (int i = deck.size () - 1 ; i >= 0 ; i--)
	{
	    if (!isFound && i == deck.size () - 1)
	    {
		if (x >= lx && x <= lx + Globals.CARD_DIM.x && y >= ly + Globals.CARD_DIM.x * 3 / 7 * i && y <= ly + Globals.CARD_DIM.x * 3 / 7 * i + Globals.CARD_DIM.y)
		{
		    isFound = true;
		    pos = i;
		}
	    }
	    else if (!isFound && i != deck.size () - 1)
	    {
		if (x >= lx && x <= lx + Globals.CARD_DIM.x && y >= ly + Globals.CARD_DIM.x * 3 / 7 * i && y <= ly + Globals.CARD_DIM.x * 3 / 7 * (i + 1))
		{
		    isFound = true;
		    pos = i;
		}
	    }
	}

	return pos;
    }


    public boolean isInPile (int x, int y)
    {
	if (x >= lx && x <= lx + Globals.CARD_DIM.x && y >= ly && y <= ly + Globals.CARD_DIM.x * 3 / 7 * (deck.size () - 1) + Globals.CARD_DIM.y)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public void fixPile (Graphics g)  // sets the locations of the cards underneath the original
    {
	if (deck.size () > 0)
	{
	    for (int i = 0 ; i < deck.size () ; i++)
	    {
		((CardClass) deck.get (i)).setCentre (lx + (Globals.CARD_DIM.x / 2), ly + (Globals.CARD_DIM.y / 2) + i * (Globals.CARD_DIM.x * 3 / 7));
	    }
	}
    }


    public void fixStack (Graphics g)
    {
	if (deck.size () > 0)
	{
	    for (int i = 0 ; i < deck.size () ; i++)
	    {
		((CardClass) deck.get (i)).setCentre (lx + (Globals.CARD_DIM.x / 2), ly + (Globals.CARD_DIM.y / 2));
	    }
	}
    }


    public void drawPile (Graphics g, boolean pile)
    {
	if (pile)
	{
	    fixPile (g);
	    for (int i = 0 ; i < deck.size () ; i++)
	    {
		CardClass c1 = (CardClass) deck.get (i);
		c1.draw (g);
	    }
	}
	else
	{
	    fixStack (g);
	    for (int i = 0 ; i < deck.size () ; i++)
	    {
		CardClass c1 = (CardClass) deck.get (i);
		c1.draw (g);
	    }
	}
    }


    public void addPile (PileClass p)
    {
	int size = p.getSize ();
	for (int i = 0 ; i < size ; i++)
	{
	    deck.addElement (p.dealCard (0));
	}
    }


    public PileClass kingSort ()
    {
	PileClass newPile = new PileClass ();
	newPile.setLeftPoint (lx, ly);
	// there's probably an easier way of doing this but I can't think of one yet so I'll do it a longer but dumber way
	boolean[] isKing = new boolean [4];
	for (int i = 0 ; i < 4 ; i++)
	{
	    if (((CardClass) deck.get (i)).getValue () == 13)
	    {
		isKing [i] = true;
	    }
	}
	for (int i = 0 ; i < 4 ; i++)
	{
	    if (isKing [i])
	    {
		newPile.addCard (((CardClass) deck.get (i)));
	    }
	}
	for (int i = 0 ; i < 4 ; i++)
	{
	    if (!isKing [i])
	    {
		newPile.addCard (((CardClass) deck.get (i)));
	    }
	}
	return newPile;
    }


    /*public void PileDance ()
    {
	int initialLx = lx;
	int initialLy = ly;
	CardClass c = (CardClass) deck.get (12);

    }
    */
}


