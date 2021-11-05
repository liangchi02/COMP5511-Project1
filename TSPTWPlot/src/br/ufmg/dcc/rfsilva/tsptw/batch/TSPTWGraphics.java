package br.ufmg.dcc.rfsilva.tsptw.batch;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

public class TSPTWGraphics
{
	private Random rand = new Random(556);

	private List<Customer> list;
	private List<Integer> solution;
	private double distance;

	private int maxX = 0;
	private int maxY = 0;

	private int rectWidth = 800;
	private int rectHeight = 600;
	private boolean showDots = true;
	private boolean showDotsLabel = true;
	private boolean showLines = true;
	private boolean showDistance = true;
	private boolean showSolutionString = true;

	public TSPTWGraphics(List<Customer> list, List<Integer> solution,
			double distance)
	{
		super();
		this.list = list;
		this.solution = solution;
		this.distance = distance;
		for (Customer c : list)
		{
			this.maxX = Math.max(c.getX(), this.maxX);
			this.maxY = Math.max(c.getY(), this.maxY);
		}
		System.out.println("maxX = " + this.maxX);
		System.out.println("maxY = " + this.maxY);
	}

	public BufferedImage paintToImage(String title)
	{
		BufferedImage bufferedImage = new BufferedImage(this.rectWidth,
				this.rectHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferedImage.createGraphics();
		g.setColor(Color.white);

		g.fillRect(0, 0, this.rectWidth, this.rectHeight);
		this.paint(title, g);
		g.dispose();
		return bufferedImage;
	}

	public void paint(String title, Graphics g)
	{
		int width = rectWidth - 60;
		int height = rectHeight - 80;
		this.rand = new Random(556);

		g.setColor(Color.black);

		// customers
		for (Customer c : this.list)
		{
			int x = (int) Math
					.floor(((double) ((double) c.getX() / (double) this.maxX))
							* width) + 10;
			int y = (int) Math
					.floor(((double) ((double) c.getY() / (double) this.maxY))
							* height) + 50;
			g.setColor(this.randomColor());
			if (this.showDots)
			{
				g.fillOval(x, y, 10, 10);
			}
			if (this.showDotsLabel)
			{
				g.drawString(String.valueOf(c.getNumber()), x + 20, y + 20);
			}
		}

		// solution
		g.setColor(Color.black);

		StringBuilder sb = new StringBuilder();
		if (this.showDistance && this.showSolutionString)
		{
			sb.append(title + " (" + this.distance + ")" + ": ");
		}
		else if (this.showDistance)
		{
			sb.append(title + " " + this.distance);
		}

		if (this.showSolutionString)
		{
			for (int i = 0; i < this.solution.size(); i++)
			{
				Customer c1 = this.find(this.solution.get(i));
				sb.append(c1.getNumber());
				sb.append(" ");
			}
		}
		String s = sb.toString();
		int ypos = 13;
		int max = (int) Math.floor((double) width / 6.0);
		while (s.length() > max)
		{
			int maxaux = max;
			while (!s.substring(maxaux, maxaux + 1).equals(" "))
			{
				maxaux--;
			}
			g.drawString(s.substring(0, maxaux), 5, ypos);
			s = s.substring(maxaux);
			ypos = ypos + 12;
		}
		g.drawString(s, 5, ypos);

		if (this.showLines)
		{
			// lines
			int inc = 100;
			int calc = inc;
			int red = calc;
			int green = calc;
			int blue = calc;

			for (int i = 0; i < this.solution.size(); i++)
			{
				calc = 0 + (int) Math
						.floor((double) ((double) i / (double) this.solution
								.size()) * 254);

				red = calc;

				g.setColor(new Color(red, green, blue));
				Customer c1 = this.find(this.solution.get(i));
				int next = i + 1;
				if (i == this.solution.size() - 1)
				{
					next = 0;
				}
				Customer c2 = this.find(this.solution.get(next));
				int x1 = (int) Math
						.floor(((double) ((double) c1.getX() / (double) this.maxX))
								* width) + 10 + 5;
				int y1 = (int) Math
						.floor(((double) ((double) c1.getY() / (double) this.maxY))
								* height) + 50 + 5;
				int x2 = (int) Math
						.floor(((double) ((double) c2.getX() / (double) this.maxX))
								* width) + 10 + 5;
				int y2 = (int) Math
						.floor(((double) ((double) c2.getY() / (double) this.maxY))
								* height) + 50 + 5;
				g.drawLine(x1, y1, x2, y2);
			}
		}
	}

	private Customer find(int number)
	{
		return list.get(number - 1);
	}

	private Color randomColor()
	{
		return (new Color(rand.nextInt(256), rand.nextInt(256), rand
				.nextInt(256)));
	}
}
