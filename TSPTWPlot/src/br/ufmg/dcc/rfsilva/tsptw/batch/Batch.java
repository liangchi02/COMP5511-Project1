package br.ufmg.dcc.rfsilva.tsptw.batch;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;


public class Batch
{
	public static void main(String args[]) throws Exception
	{
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Directory with instances and solutions file");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setDialogType(JFileChooser.OPEN_DIALOG);
		int r = jfc.showOpenDialog(null);
		if (r == JFileChooser.APPROVE_OPTION)
		{
			File file = jfc.getSelectedFile();
			File childs[] = file.listFiles();
			for (File instance : childs)
			{
				File solution = new File(instance.getAbsolutePath()
						+ ".solution");
				if (instance.getName().endsWith(".txt") && solution.exists())
				{
					SolutionFileParser sfp = new SolutionFileParser();
					sfp.parse(solution);
					
					TSPTWGraphics tg = new TSPTWGraphics(InstanceFileParser
							.parse(instance), sfp.getPath(), sfp.getPathDistance());

					BufferedImage bf = tg.paintToImage(instance
							.getName().replace(".txt", ""));
					ImageIO.write(bf, "png", new File(instance
							.getAbsolutePath().replace(".txt", ".png")));
				}
			}
		}
	}
}
