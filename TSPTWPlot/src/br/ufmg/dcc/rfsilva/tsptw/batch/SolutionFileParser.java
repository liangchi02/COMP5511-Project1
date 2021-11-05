package br.ufmg.dcc.rfsilva.tsptw.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SolutionFileParser
{
	public List<Integer> path;
	public double pathDistance;

	public void parse(File file) throws Exception
	{
		this.path = new ArrayList<Integer>();
		try
		{
			BufferedReader bfr = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
			String line;
			// path
			if ((line = bfr.readLine()) != null)
			{
				sb.append(line);
				sb.append(" ");
			}
			StringTokenizer st = new StringTokenizer(sb.toString());
			while (st.hasMoreElements())
			{
				String t = st.nextToken();
				this.path.add(Integer.valueOf(t.trim()));
			}
			// distance
			if ((line = bfr.readLine()) != null)
			{
				this.pathDistance = Double.valueOf(line);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	public List<Integer> getPath()
	{
		return path;
	}

	public double getPathDistance()
	{
		return pathDistance;
	}

}
