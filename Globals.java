// Author : Nathan Li
// ICS4U Final Project
// Baker's Dozen Solitaire
import java.awt.*;
import java.util.*;

//this class holds global variables and constants.
public class Globals
{
    //card dimensions
    static Point CARD_DIM = new Point (70, 100);

    //images
    static Image CARD_UP_IMG;
    static Image CARD_DOWN_IMG;

    //enumeration of suits
    final static int CLUBS = 0;
    final static int DIAMONDS = 1;
    final static int SPADES = 2;
    final static int HEARTS = 3;

    //location of the 13 piles
    public static Vector p ()
    {
	Vector PILE = new Vector (0, 1);
	PILE.addElement (new Point (50, 300));
	PILE.addElement (new Point (150, 300));
	PILE.addElement (new Point (250, 300));
	PILE.addElement (new Point (350, 300));
	PILE.addElement (new Point (450, 300));
	PILE.addElement (new Point (550, 300));
	PILE.addElement (new Point (650, 300));
	PILE.addElement (new Point (100, 700));
	PILE.addElement (new Point (200, 700));
	PILE.addElement (new Point (300, 700));
	PILE.addElement (new Point (400, 700));
	PILE.addElement (new Point (500, 700));
	PILE.addElement (new Point (600, 700));
	return PILE;
    }


    //location of the 4 stacks at the top
    public static Vector s ()
    {
	Vector STACK = new Vector (0, 1);
	STACK.addElement (new Point (200, 100));
	STACK.addElement (new Point (300, 100));
	STACK.addElement (new Point (400, 100));
	STACK.addElement (new Point (500, 100));
	return STACK;
    }


    //the suits for the 4 stacks
    final static DiamondClass d1 = new DiamondClass (35, 50, 235, 150, Color.red);
    final static ClubClass c1 = new ClubClass (35, 50, 335, 150, Color.black);
    final static HeartClass h1 = new HeartClass (35, 50, 435, 150, Color.red);
    final static SpadeClass s1 = new SpadeClass (35, 50, 535, 150, Color.black);

    //an error leg in case something catchable occurs
    static Vector ERROR_LOG = new Vector ();

    //a random variable
    static Random rand = new Random ();
}
