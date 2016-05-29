import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PracticeDraw extends JWindow{
	static final int WIDTH = 600;
	static final int HEIGHT = 600;
	public PracticeDraw()
	{
		getContentPane().add(new GraphicPanel(WIDTH,HEIGHT));
	}
	public static void main (String [] args)
	{
		PracticeDraw window = new PracticeDraw();
		window.setLocation(0,0);
		window.setSize(WIDTH,HEIGHT);
		window.setVisible(true);
	}
}
class GraphicPanel extends JPanel
{
	private int WIDTH ;
	private int HEIGHT ;
	GraphicPanel(int width,int height)
	{
		super();
		WIDTH = width;
		HEIGHT = height;
	}
	protected void paintComponent(Graphics g)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int stepLength =sc.nextInt(); 
		int blockNum = sc.nextInt();
		super.paintComponent(g);
		Circle.setStepLength(stepLength);
		Circle.setHeight(HEIGHT);
		Circle.setWidth(WIDTH);
		ArrayList <Circle> cirList = new ArrayList <Circle>();
		Circle.insertNRandomBlock(cirList, blockNum);
		g.setColor(Color.BLUE);
		for(int count = 0;count <blockNum;count++)
			blockPainter(g,cirList.get(count));
		g.setColor(Color.RED);
		int radio = WIDTH/2;
		while(cirList.size()<n+blockNum)
		{
			for(int nextX = 1;nextX<WIDTH;nextX+=stepLength)
			{
				if ((nextX-radio)<0||(nextX+radio)>WIDTH)
					continue;
				for(int nextY = 1;nextY<HEIGHT;nextY+=stepLength)
				{
					if((nextY-radio)<0||(nextY+radio)>HEIGHT)
						continue;
					Circle c = new Circle(nextX,nextY,radio);
					if (!c.AllCirCheck(cirList))
						continue;
					cirList.add(c);
					circlePainter(g,c);
				}
			}
			radio -=stepLength;
			if (radio == 0)
				break;
		}
	}
	public void circlePainter(Graphics g ,Circle c)
	{
		g.fillArc(c.getX()-c.getRadio(), 
				c.getY()-c.getRadio(),
				c.getRadio()*2, c.getRadio()*2, 0, 360);
	}
	public void blockPainter (Graphics g ,Circle c)
	{
		g.fillArc(c.getX()-3, 
				c.getY()-3,
				6, 6, 0, 360);
	}
}