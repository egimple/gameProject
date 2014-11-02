package gameplay;

import javax.swing.JFrame;

public class Bomberman extends JFrame
{
	public Bomberman()
	{
		add(new Map());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(775, 345);
		setLocationRelativeTo(null);
		setTitle("Bomberman");
		setResizable(true);
		setVisible(true);	
	}
	

	public static void main(String[] args)
	{
		new Bomberman();
	}

}