import java.text.DecimalFormat;
import java.util.*;
public class Data {
    public static double[][] DistMatrix(){
        double [][] xy = new double [][] {
                {    0.3642    ,    0.7770    }    ,
                {    0.7185    ,    0.8312    }    ,
                {    0.0986    ,    0.5891    }    ,
                {    0.2954    ,    0.9606    }    ,
                {    0.5951    ,    0.4647    }    ,
                {    0.6697    ,    0.7657    }    ,
                {    0.4353    ,    0.1709    }    ,
                {    0.2131    ,    0.8349    }    ,
                {    0.3479    ,    0.6984    }    ,
                {    0.4516    ,    0.0488    }    ,
        };
        int N = xy.length;
        double[][] DM = new double[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double b = 100*Math.hypot(xy[i][0] - xy[j][0],xy[i][1] - xy[j][1]);
                DecimalFormat df = new DecimalFormat("#.##");
                DM[i][j]= Double.parseDouble(df.format(b));
            }
        }
        return DM;
    }
}
