import java.math.*;
import java.util.ArrayList;;;
public class Circle 
{
	static private int stepLength;
	private int radio;
	private int x;
	private int y;
	Circle(int x,int y,int radio)
	{
		setX(x);
		setY(y);
		setRadio(radio);
	}
	
	public boolean CheckOverlap(Circle c)
	{
		if (Math.sqrt((c.x-x)*(c.x-x)+(c.y-y)*(c.y-y))>c.radio+radio)
			return true;
		return false;
	}
	
	public boolean AllCirCheck(ArrayList <Circle> cirList)
	{
		boolean re = true;
		for(int i = 0 ; i < cirList.size();i++)
			if ((re = CheckOverlap(cirList.get(i)))==false)
				break;
		return re;
	}
	
	public static void setStepLength(int stepLength) {
		Circle.stepLength = stepLength;
	}
	
	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
