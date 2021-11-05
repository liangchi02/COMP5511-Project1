package br.ufmg.dcc.rfsilva.tsptw.batch;

public class Customer
{
	private int number;
	private int x;
	private int y;
	private int demand;
	private int ready;
	private int due;
	private int service;

	public Customer()
	{

	}

	public int getNumber()
	{
		return number;
	}

	public void setNumber(int number)
	{
		this.number = number;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getDemand()
	{
		return demand;
	}

	public void setDemand(int demand)
	{
		this.demand = demand;
	}

	public int getReady()
	{
		return ready;
	}

	public void setReady(int ready)
	{
		this.ready = ready;
	}

	public int getDue()
	{
		return due;
	}

	public void setDue(int due)
	{
		this.due = due;
	}

	public int getService()
	{
		return service;
	}

	public void setService(int service)
	{
		this.service = service;
	}

}
