package beerTycoonPack;

import javax.swing.*;

public class Alex
{
  public static void main( String[] args ) {
    JFrame frame = new JFrame( "Beer Tycoon" );
    frame.add( new TempTimer() );
	frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.setSize( 300, 300 );
    frame.setVisible( true );
  }
}


