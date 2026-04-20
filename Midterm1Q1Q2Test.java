import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JFrame;

public class Midterm1Q1Q2Test 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("ECE4319 Midterm 1 Q1 And Q2");
		GridBall testPanel = new GridBall();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(testPanel);
		frame.setSize(700, 700);
		frame.setVisible(true);
		frame.pack();
		frame.validate();
	}

}
