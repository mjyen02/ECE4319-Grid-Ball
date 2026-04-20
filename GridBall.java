import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


public class GridBall extends JPanel implements KeyListener
{
	final int grid_size = 4;
	final int cell_size = 100;
	final int offset_x = 100;
	final int offset_y = 50;
	final int pondX = offset_x + 110;
	final int pondY = offset_y + 105;
	int ballX = 0;
	int ballY = 3;
	final int ball_size = 60;
	private JLabel statusBar; // label that displays event information
	
    private final int[][] pondTiles = 
    	{
            {1, 1}, {1, 2}, {2, 1}, {2, 2}, {3, 1}, {3, 2}
        };
	
	public GridBall()
	{
		setFocusable(true);
		addKeyListener(this);
		statusBar = new JLabel( "Mouse outside JPanel" ); 
		add( statusBar, BorderLayout.SOUTH ); // add label to JFrame
	}
	
	@Override
	protected void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		
		for (int row = 0; row < grid_size; row++)
		{
			for (int col = 0; col < grid_size; col++)
			{
				int x = col * cell_size + offset_x;
				int y = row * cell_size + offset_y;
				g.drawRect(x,  y,  cell_size, cell_size);
			}
		}
		
		g.setFont(new Font("Serif", Font.PLAIN, 24));
		g.drawString("Start", offset_x - 60, offset_y + (grid_size - 1) * cell_size + cell_size/2 + 8);
		g.setColor(Color.RED);
		g.drawString("End", offset_x + (grid_size - 1) * cell_size + cell_size/2 + 8 + 60, offset_y + (grid_size - 1) * cell_size + cell_size/2 + 8);
		
        // Draw the ball
        int ballScreenX = ballX * cell_size + offset_x + 20;
        int ballScreenY = ballY * cell_size + offset_y + 20;
        g.fillOval(ballScreenX, ballScreenY, ball_size, ball_size);
		
		g.setColor(Color.BLUE);
		g.fillOval(pondX, pondY, 180, 290);
		
		g.setColor(Color.WHITE);
		g.drawString("Pond", pondX + 70, pondY + 150);
	}
	
	@Override
	public void keyPressed (KeyEvent e)
	{
        int keyCode = e.getKeyCode();
        int newBallX = ballX;
        int newBallY = ballY;

        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                newBallX = ballX > 0 ? ballX - 1 : ballX;
                statusBar.setText( String.format("Left Clicked"));
                break;
            case KeyEvent.VK_RIGHT:
                newBallX = ballX < grid_size - 1 ? ballX + 1 : ballX;
                statusBar.setText( String.format("Right Clicked"));
                break;
            case KeyEvent.VK_UP:
                newBallY = ballY > 0 ? ballY - 1 : ballY;
                statusBar.setText( String.format("Up Clicked"));
                break;
            case KeyEvent.VK_DOWN:
                newBallY = ballY < grid_size - 1 ? ballY + 1 : ballY;
                statusBar.setText( String.format("Down Clicked"));
                break;
        }

        if (!isPondTile(newBallX, newBallY)) {
            ballX = newBallX;
            ballY = newBallY;
        }

        repaint();
    }
	
    @Override
    public void keyReleased(KeyEvent event) {
        // Do nothing
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // Do nothing
    }
    
    private boolean isPondTile(int x, int y) {
        for (int[] tile : pondTiles) {
            if (tile[0] == y && tile[1] == x) {
                return true;
            }
        }
        return false;
    }

}
