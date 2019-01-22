// Author : Nathan Li
// ICS4U Final Project
// Baker's Dozen Solitaire
import java.awt.*;
import hsa.Console;
import java.util.*;

class DeckClass extends CardClass
{
    protected Vector deck = new Vector (0, 1);


    public DeckClass ()
    {
    }


    public DeckClass (char deckType)
    {
	if (deckType == 's') // std deck
	{
	    // generate 52 cards (2 nested loops) and add them to the deck
	    int count = 0;
	    for (int i = 1 ; i <= 4 ; i++)
	    {
		for (int j = 1 ; j <= 13 ; j++)
		{
		    CardClass c1 = new CardClass ();
		    c1.setSuit (i);
		    c1.setValue (j);
		    c1.setFace (2);
		    c1.setCentre (100, 100);
		    addCard (c1, count);
		    count++;
		}
	    }
	}
    }


    public boolean isEmpty ()
    {
	return deck.isEmpty ();
    }


    public void addCard (CardClass cardToAdd, int Pos)
    {
	if (Pos == 0 && deck.size () == 0)
	{
	    deck.addElement (cardToAdd);
	}
	else if (Pos > deck.size ())
	{
	    deck.insertElementAt (cardToAdd, deck.size ());
	}
	else
	{
	    deck.insertElementAt (cardToAdd, Pos);
	}
    }


    public CardClass dealCard (int Pos)
    {
	return (CardClass) deck.remove (Pos);   // must type cast element
    }


    public CardClass getCard (int Pos)
    {
	return (CardClass) deck.get (Pos);
    }


    public void shuffle ()
    {
	if (isEmpty ())
	{

	}
	else
	{
	    for (int i = 0 ; i < 1000 ; i++)
	    {
		int pos = (int) (Math.random () * deck.size () - 1);
		CardClass c1 = new CardClass ();
		c1 = getCard (pos);
		deck.remove (pos);
		addCard (c1, (int) (Math.random () * deck.size () - 1));
	    }
	}
    }


    public void setCardCentre (int x, int y, int pos)
    {
	CardClass c1 = new CardClass ();
	CardClass c2 = new CardClass ();
	c2 = (CardClass) deck.get (pos);
	c1.setValue (c2.getValue ());
	c1.setSuit (c2.getSuit ());
	c1.setFace (c2.getFace ());
	c1.setCentre (x, y);
	deck.remove (pos);
	deck.insertElementAt (c1, pos);
    }


    public void decCentre (int x, int y, int pos)
    {
	CardClass c1 = new CardClass ();
	CardClass c2 = new CardClass ();
	c2 = (CardClass) deck.get (pos);
	c1.setValue (c2.getValue ());
	c1.setHeight (c2.getHeight ());
	c1.setSuit (c2.getSuit ());
	c1.setFace (c2.getFace ());
	c1.setCentre (x, y);
	deck.remove (pos);
	deck.insertElementAt (c1, pos);
	if (pos < deck.size () - 1)
	{
	    decCentre (x, y, pos + 1);
	}

    }


    public void decCentreCaller (int x, int y, int pos)
    {
	if (isEmpty ())
	{

	}
	else
	{
	    decCentre (x, y, pos);
	}
    }
}
