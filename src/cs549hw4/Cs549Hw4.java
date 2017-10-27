/* Kevin Leehan
 * CS549
 * HW4 - Unsupervised Learning
 */
package cs549hw4;
public class Cs549Hw4 {
    
    public static void main(String[] args) {
        //Name and class information for the output page
        System.out.println("Kevin Leehan\nCS549\nHW4\nUnsupervised Learning\n");
        
        double learningCoefficient = 0.5;
        int finalEpoch=100;
        //Training Data
        double[][] input = {
        {1,0,0,0,0,0},  //0-cat
        {1,0,0,1,0,0},  //1-bat
        {1,0,0,0,1,0},  //2-whale
        {0,0,1,1,0,1},  //3-canary
        {0,0,1,1,0,1},  //4-robin
        {0,0,1,0,0,1},  //5-ostrich
        {0,1,0,0,0,1},  //6-snake
        {0,1,0,0,0,1},  //7-lizard
        {0,1,0,0,1,1},  //8-aligator
        {1,0,0,0,0,0}}; //9-dog
                
        //Testing Data
        double[] testInput = {1,0,0,0,1,1};  //10-platypus
        
        //Create an instance of UnsupervisedLearning to create Neural Network
        UnsupervisedLearning run = new UnsupervisedLearning();
        
        //Sets the randoms weights and learning coefficient
        run.initializer(input,learningCoefficient);
        //Run Training data
        for(int i=0; i < finalEpoch; i++)
            run.train(input);
        //Print Training Results
        run.trainingResults();
        //Run Test Input Data
        run.test(testInput);
        //Print Test Results
        run.results();
    }
}
