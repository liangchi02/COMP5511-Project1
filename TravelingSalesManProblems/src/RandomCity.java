import java.text.DecimalFormat;
import java.util.Random;

import static java.lang.StrictMath.random;

public class RandomCity {
    public static double[][] DistMatrix(){
        Random random = new Random();
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
                //random city coordinate by excel
                {0.9221,0.1506},
                {0.7713,0.3575},
                {0.8054,0.6862},
                {0.8179,0.8084},
                {0.8145,0.4101},
                {0.8195,0.3333},
                {0.5717,0.9509},
                {0.2487,0.9382},
                {0.6115,0.3835},
                {0.2390,0.9206},

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
