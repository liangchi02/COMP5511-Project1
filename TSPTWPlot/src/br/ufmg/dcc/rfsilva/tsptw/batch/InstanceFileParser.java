package br.ufmg.dcc.rfsilva.tsptw.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class InstanceFileParser
{
	public static List<Customer> parse(File file) throws Exception
	{
		ArrayList<Customer> list = new ArrayList<Customer>();
		BufferedReader bfr = new BufferedReader(new FileReader(file));
		String line;
		while ((line = bfr.readLine()) != null)
		{
			Customer p = InstanceFileParser.parseLine(line);
			if (p != null && p.getNumber() != 0 && p.getNumber() != 999)
			{
				list.add(p);
			}
		}
		return list;
	}

	private static Customer parseLine(String s) throws Exception
	{
		Customer p = new Customer();
		try
		{
			StringTokenizer st = new StringTokenizer(s);
			p.setNumber(Integer.valueOf(st.nextToken().trim()));
			System.out.print(p.getNumber() + "	");
			p.setX(Double.valueOf(st.nextToken().trim()).intValue());
			System.out.print(p.getX() + "	");
			p.setY(Double.valueOf(st.nextToken().trim()).intValue());
			System.out.print(p.getY() + "	");
			p.setDemand(Double.valueOf(st.nextToken().trim()).intValue());
			System.out.print(p.getDemand() + "	");
			p.setReady(Double.valueOf(st.nextToken().trim()).intValue());
			System.out.print(p.getReady() + "	");
			p.setService(Double.valueOf(st.nextToken().trim()).intValue());
			System.out.print(p.getService() + "	");
			System.out.println();
		}
		catch (Exception e)
		{
			return null;
		}
		return p;
	}
}
