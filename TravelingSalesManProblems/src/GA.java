import java.lang.reflect.Array;
import java.util.*;

public class GA {
    private int generationSize;
    private int genomeSize;
    private int numberOfCities;
    private int reproductionSize;
    private int maxIterations;
    private float mutationRate;
    private int tournamentSize;
    private SelectionType selectionType;
    private double[][] travelPrices;
    private int startingCity;
    private int targetFitness;

    public GA(int numberOfCities, SelectionType selectionType, double[][] travelPrices, int startingCity, int targetFitness){
        this.numberOfCities = numberOfCities;
        this.genomeSize = numberOfCities-1;
        this.selectionType = selectionType;
        this.travelPrices = travelPrices;
        this.startingCity = startingCity;
        this.targetFitness = targetFitness;

        generationSize = 3000;
        reproductionSize = 800;
        maxIterations = 5000;
        mutationRate = 0.1f;
        tournamentSize = 180;

    }

    public List<Genome> initialPopulation(){
        List<Genome> population = new ArrayList<>();
        for(int i=0; i<generationSize; i++){
            population.add(new Genome(numberOfCities, travelPrices, startingCity));
        }
        return population;
    }

    public List<Genome> selection(List<Genome> population){
        List<Genome> selected = new ArrayList<>();
        Genome winner;
        for(int i=0; i<reproductionSize; i++){
            if(selectionType == SelectionType.ROULETTE){
                selected.add(rouletteSelection(population));
            }
            else if(selectionType == SelectionType.TOURNAMENT){
                selected.add(tournamentSelection(population));
            }
        }

        return selected;
    }

    public Genome rouletteSelection(List<Genome> population){
        int totalFitness = population.stream().map(Genome::getFitness).mapToInt(Integer::intValue).sum();
        Random random = new Random();
        int selectedValue = random.nextInt(totalFitness);
        float recValue = (float) 1/selectedValue;
        float currentSum = 0;
        for(Genome genome : population){
            currentSum += (float) 1/genome.getFitness();
            if(currentSum>=recValue){
                return genome;
            }
        }
        int selectRandom = random.nextInt(generationSize);
        return population.get(selectRandom);
    }

    public static <E> List<E> pickNRandomElements(List<E> list, int n) {
        Random r = new Random();
        int length = list.size();

        if (length < n) return null;

        for (int i = length - 1; i >= length - n; --i)
        {
            Collections.swap(list, i , r.nextInt(i + 1));
        }
        return list.subList(length - n, length);
    }

    public Genome tournamentSelection(List<Genome> population){
        List<Genome> selected = pickNRandomElements(population,tournamentSize);
        return Collections.min(selected);
    }

    public Genome mutate(Genome salesman){
        Random random = new Random();
        float mutate = random.nextFloat();
        if(mutate<mutationRate) {
            List<Integer> genome = salesman.getGenome();
            Collections.swap(genome, random.nextInt(genomeSize), random.nextInt(genomeSize));
            return new Genome(genome, numberOfCities, travelPrices, startingCity);
        }
        return salesman;
    }

    public List<Genome> createGeneration(List<Genome> population){
        List<Genome> generation = new ArrayList<>();
        int currentGenerationSize = 0;
        while(currentGenerationSize < generationSize){
            List<Genome> parents = pickNRandomElements(population,2);
            List<Genome> children = crossover(parents);
            children.set(0, mutate(children.get(0)));
            children.set(1, mutate(children.get(1)));
            generation.addAll(children);
            currentGenerationSize+=2;
        }
        return generation;
    }

    public List<Genome> crossover(List<Genome> parents){

        Random random = new Random();
        int breakpoint = random.nextInt(genomeSize);
        List<Genome> children = new ArrayList<>();

        List<Integer> parent1Genome = new ArrayList<>(parents.get(0).getGenome());
        List<Integer> parent2Genome = new ArrayList<>(parents.get(1).getGenome());

        for(int i = 0; i<breakpoint; i++){
            int newVal;
            newVal = parent2Genome.get(i);
            Collections.swap(parent1Genome,parent1Genome.indexOf(newVal),i);
        }
        children.add(new Genome(parent1Genome,numberOfCities,travelPrices,startingCity));
        parent1Genome = parents.get(0).getGenome();

        for(int i = breakpoint; i<genomeSize; i++){
            int newVal = parent1Genome.get(i);
            Collections.swap(parent2Genome,parent2Genome.indexOf(newVal),i);
        }
        children.add(new Genome(parent2Genome,numberOfCities,travelPrices,startingCity));

        return children;
    }

    public Genome optimize(){
        List<Genome> population = initialPopulation();
        Genome globalBestGenome = population.get(0);
        for(int i=0; i<maxIterations; i++){
            List<Genome> selected = selection(population);
            population = createGeneration(selected);
            globalBestGenome = Collections.min(population);
            if(globalBestGenome.getFitness() < targetFitness)
                break;
        }
        return globalBestGenome;
    }


    //use population filter to select genome satisfy a certain order
    public List<Genome> PopulationFilter(List<Genome> population,int city1, int city2, int city3) {
        List<Genome> SOPPopulation = new ArrayList<>();
        String currentOrder = "";
        String Order = String.valueOf(city1)+city2+city3;
        for (int i = 0; i < population.size(); i++) {
            List<Integer> genome = population.get(i).genome;
            for (int i1 = 0; i1 < genome.size(); i1++) {
                int num = genome.get(i1);
                if (num == city1) {
                    currentOrder = currentOrder + city1;
                }else if (num == city2){
                    currentOrder = currentOrder + city2;
                }
                else if (num == city3){
                    currentOrder = currentOrder + city3;
                };
            }
            if (currentOrder.equals(Order)) {
                SOPPopulation.add(population.get(i));
            }
            currentOrder = "";
        }
        return SOPPopulation;
    }

    public Genome optimizeSOP(int city1, int city2, int city3){
        List<Genome> population = initialPopulation();
        Genome globalBestGenome = population.get(0);
        for(int i=0; i<maxIterations; i++){
            List<Genome> selected = selection(population);
            population = createGeneration(selected);
            //start filter step
            List<Genome> result = PopulationFilter(population, city1, city2, city3);
            globalBestGenome = Collections.min(result);
            if(globalBestGenome.getFitness() < targetFitness)
                break;
        }
        return globalBestGenome;
    }

}

