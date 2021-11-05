import java.text.DecimalFormat;

public class DummyCity {
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
        // add dummy city to travel price matrix
        // set 0-city as a start city and 9-city as an end city
        double[][] dummyDM = new double[N+1][N+1];
        for (int i = 0; i < N+1; i++) {
            for (int j = 0; j < N+1; j++) {
                if(i<N&&j<N){ dummyDM[i][j]=DM[i][j];}
                if((i==N&&j==1)||(i==N&&j==N-1)||(i==N&&j==N)){dummyDM[i][j]=0;}
                if((j==N&&i==1)||(j==N&&i==N-1)||(j==N&&i==N)){dummyDM[i][j]=0;}
                if((i==N&&j>=1&&j<=N-2)||(j==N&&i>=1&&i<=N-2)){dummyDM[i][j]=999999;}

            }
        }

        return dummyDM;
    }

//    public static void main(String[] args) {
//        double[][] doubles = DistMatrix();
//        System.out.println(doubles[doubles.length-1][doubles.length-3]);
//    }
}
