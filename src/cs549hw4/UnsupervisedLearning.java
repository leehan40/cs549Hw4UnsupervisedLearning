/* Kevin Leehan
 * CS549
 * HW4 - Unsupervised Learning
 */
package cs549hw4;
import java.util.Arrays;
import java.util.Random;

public class UnsupervisedLearning {
    int inputUnits; //number of inputs
    int outputUnits=3;  //number of outputs
    double[][] iWeights, weights; //initial weights and changing weights
    double n;  //learningCoefficient
    int m;  //# of active input units in the input vector
    String[] winnerList;  //keeps track of what group each Object belongs
    int winner; //output with the highest netInput value
    
    //Initializes the number of input/output units and sets the initial weights
    //as well as the learning coefficient
    public void initializer(double [][] a, double b){
        inputUnits = a[0].length;
        n = b; //set learningCoefficient
        //iWeights will only be used to print initial weights
        iWeights = new double [outputUnits][inputUnits];
        weights = new double [outputUnits][inputUnits]; 
        //Setting weights between output and input layers
        for(int i = 0; i<outputUnits; i++){
            iWeights[i] = setRandomWeights();
            //saves a copy for initial weights
            System.arraycopy(iWeights[i], 0, weights[i], 0, inputUnits); 
        }
    }
    // setRandomWeights
    //creates an array of random numbers betweeen .01 and .99, which sum to 1.0
    private double[] setRandomWeights(){
        double[] a = null;
        double sum2 = 0;
        while(sum2 != 1){ 
            double sum = 0;
            sum2 = 0;
            a = new double[6];
            int max = 99;
            int min = 1;
            for(int i=0; i<a.length; i++){
                Random rn = new Random();
                a[i] = rn.nextInt(max-min+1)+min;
                sum = sum + a[i];              
            }
            for(int i=0; i<a.length; i++){
                a[i] = a[i] / sum;
                a[i] = Math.round((a[i])* 100.0) / 100.0;
                sum2 = sum2 + a[i];
            }          
        }return a;
    }
    
    // findm
    //returns the sum of the active inputs in an input vector
    public int findm(double[] array){
        int t=0;
            //adds all the 1 values in the given input/object
            for(int i = 0; i < array.length; i++) 
                t += array[i];
        return t;
    }
    
    // findWinner
    //returns the winning node for a particular input vector
    public int findWinner(double[][] weights, double[] vector){
        //find group winner // i cycles through each output
        int t = 0;      // j cycles through the weights of object i
        double netInput = 0;
        for(int i = 0; i < outputUnits; i++){
            double sum = 0;
            for(int j  = 0; j < weights[i].length; j++) //sum of (weights*input)
                sum += (weights[i][j]*vector[j]);
            if(sum > netInput){
                t = i;
                netInput = sum;
            }
        } return t;
    }
    
    //Trains the neural network using the 2D array "input"
    public void train(double[][] a){
        double delta; //delta of weight
        double cw; //current weight
        winnerList = new String [a.length];
        
        // loop z cycles through each object
        for(int z = 0; z < a.length; z++){
            // calculates the # of active input units in input vector
            m = findm(a[z]);
                //testing System.out.println("sum of 1's in object"+z+" = "+m);
            //Finds the winning output node
            winner = findWinner(weights,a[z]);
            //Records the winning output node for each input vector
            winnerList[z] = " "+winner;
            
            //Update winner's weights
            double I;
            for(int k = 0; k < weights[winner].length; k++){
                cw = weights[winner][k]; //Weight to be updated
                I = a[z][k]; //numerator (1 or 0)
                I=I/m;
                I = Math.floor(I*100)/100;
                delta = n*( I - cw);//delta of weight
                //System.out.println("cw= "+cw+" delta= "+delta);
                weights[winner][k] = Math.round((cw + delta)* 100.0) / 100.0;
            }
        }
    }
    //prints out the what group each input vector belongs
    public void trainingResults(){
        System.out.println("\nTraining results:");
        String[] animal={"      Cat", "      Bat", "    Whale", "   Canary", 
                         "    Robin", "  Ostrich", "    Snake", "   Lizard", 
                         "Alligator", "      Dog"};
        for(int i=0; i<10; i++)
            System.out.println(animal[i]+" belongs to group"+winnerList[i]);
    }
    
    //calculates and prints out the group the testInput belongs to
    public void test(double[] a){
        int group = findWinner(weights,a);
        System.out.println("\nTest results:");
        System.out.println(" Platypus belongs to group "+group);        
    }
    
    //Prints the learning coefficient, initial weights, and final weights
    public void results(){
        System.out.println("\nParameters:");
        System.out.println("Learning Coefficient = "+n);
        System.out.println("Initial Weights: "+
                                        Arrays.deepToString(iWeights));
        System.out.println("  Final Weights: "+Arrays.deepToString(weights));
    }
}   
