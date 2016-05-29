import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PracticeDraw extends JWindow{
	public PracticeDraw()
	{
		getContentPane().add(new GraphicPanel());
	}
	public static void main (String [] args)
	{
		PracticeDraw window = new PracticeDraw();
		window.setLocation(0,0);
		window.setSize(600,600);
		window.setVisible(true);
	}
}
class GraphicPanel extends JPanel
{
	protected void paintComponent(Graphics g)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int stepLength =sc.nextInt(); 
		super.paintComponent(g);
		g.setColor(Color.RED);
		ArrayList <Circle> cirList = new ArrayList <Circle>();
		cirList.add(new Circle(getWidth()/2,getWidth()/2,getWidth()/2));
		Circle first = cirList.get(0);
		first.setStepLength(stepLength);
		g.fillArc(first.getX()-first.getRadio()-1, 
				first.getY()-first.getRadio()-1,
				first.getRadio()*2, first.getRadio()*2, 0, 360);;
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int radio = first.getRadio()/6;
		while(cirList.size()<n)
		{
			for(int nextX = 1;nextX<600;nextX+=stepLength)
			{
				if ((nextX-radio)<0||(nextX+radio)>600)
					continue;
				for(int nextY = 1;nextY<600;nextY+=stepLength)
				{
					if((nextY-radio)<0||(nextY+radio)>600)
						continue;
					//if (robot.getPixelColor(nextX, nextY).getAlpha()==Color.RED.getAlpha())
						//continue;
					Circle c = new Circle(nextX,nextY,radio);
					if (!c.AllCirCheck(cirList))
						continue;
					cirList.add(c);
					g.fillArc(c.getX()-c.getRadio(), 
							c.getY()-c.getRadio(),
							c.getRadio()*2, c.getRadio()*2, 0, 360);;
				}
			}
			radio -=stepLength;
			if (radio == 0)
				break;
		}
	}
}