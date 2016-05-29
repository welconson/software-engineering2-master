import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
		BufferedImage image= (BufferedImage) this.createImage(WIDTH, HEIGHT);
		Graphics imageGra = image.getGraphics();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int stepLength =sc.nextInt(); 
		int blockNum = sc.nextInt();
		super.paintComponent(imageGra);
		Circle.setStepLength(stepLength);
		Circle.setHeight(HEIGHT);
		Circle.setWidth(WIDTH);
		ArrayList <Circle> cirList = new ArrayList <Circle>();
		Circle.insertNRandomBlock(cirList, blockNum);
		imageGra.setColor(Color.BLUE);
		for(int count = 0;count <blockNum;count++)
			blockPainter(imageGra,cirList.get(count));
		imageGra.setColor(Color.RED);
		int radio = WIDTH/2;
		long count=0;
		while(cirList.size()<n+blockNum)
		{
			for(int nextX = 1;nextX<WIDTH;nextX+=stepLength)
			{
				if ((nextX-radio)<0||(nextX+radio)>WIDTH)
					continue;
				for(int nextY = 1;nextY<HEIGHT;nextY+=stepLength)
				{
					if(cirList.size()>=n+blockNum)
						break;
					if((nextY-radio)<0||(nextY+radio)>HEIGHT)
						continue;
					int pointColor=image.getRGB(nextX, nextY);
					if(Color.RED.getRGB()==pointColor)
						{count++;
						continue;}
					Circle c = new Circle(nextX,nextY,radio);
					if (!c.AllCirCheck(cirList))
						continue;
					cirList.add(c);
					circlePainter(imageGra,c);
				}
			}
			radio -=stepLength;
			if (radio == 0)
				break;
		}
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, 0, 0, WIDTH, HEIGHT, this);
		System.out.println(count);
	}
	public void circlePainter(Graphics g ,Circle c)
	{
		g.fillArc(c.getX()-c.getRadio(), 
				c.getY()-c.getRadio(),
				c.getRadio()*2, c.getRadio()*2, 0, 360);
	}
	public void blockPainter (Graphics g ,Circle c)
	{
		g.fillArc(c.getX()-5, 
				c.getY()-5,
				10, 10, 0, 360);
	}
}