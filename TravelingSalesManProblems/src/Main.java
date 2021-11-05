import java.util.*;

public class Main {
    public static void printTravelPrices(double[][] travelPrices, int numberOfCities){
        for(int i = 0; i<numberOfCities; i++){
            for(int j=0; j<numberOfCities; j++){
                System.out.print(travelPrices[i][j]);
                if(travelPrices[i][j]/10 == 0)
                    System.out.print("  ");
                else
                    System.out.print(' ');
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        //10 fixed cities code
        Data data = new Data();
        double[][] travelPrices = data.DistMatrix();

        //10 fixed cities and 10 random cities code
//        RandomCity randomCity = new RandomCity();
//        double[][] travelPrices = randomCity.DistMatrix();

        //dummy city code
//        DummyCity dummyCity = new DummyCity();
//        double[][] travelPrices = dummyCity.DistMatrix();

        //ATSP with 5 cities
//        ATSP atsp = new ATSP();
//        double[][] travelPrices = atsp.DistMatrix();

        int numberOfCities = travelPrices.length;
        System.out.println(numberOfCities);
        printTravelPrices(travelPrices,numberOfCities);

        //dummy city
//        GA geneticAlgorithm = new GA(numberOfCities, SelectionType.TOURNAMENT, travelPrices, travelPrices.length-1, 0);
//        Genome result = geneticAlgorithm.optimize();

        //ATSP
//        GA geneticAlgorithm = new GA(numberOfCities, SelectionType.TOURNAMENT, travelPrices, 5, 0);
//        Genome result = geneticAlgorithm.optimize();


        //in order city
        GA geneticAlgorithm = new GA(numberOfCities, SelectionType.TOURNAMENT, travelPrices, 0, 0);
        Genome result = geneticAlgorithm.optimizeSOP(1,5,2);



        System.out.println(result);
    }
}

