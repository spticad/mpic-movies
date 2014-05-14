package algo;

import models.Rating;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alina
 * Date: 13.05.14
 * Time: 19:02
 * To change this template use File | Settings | File Templates.
 */
public class RatingCountMatrix {
    public int[][] matrix;
    public int totalCount;
    public int similarityCount;

    public RatingCountMatrix(int size) {
        this.matrix = new int[size][size];
        this.totalCount = 0;
        this.similarityCount = 0;
    }

    public void initMatrix(List<Rating> ratingsByUserA, List<Rating> ratingsByUserB) {
        for (int i = 0; i<ratingsByUserA.size(); i++){
            Rating ratingByA = ratingsByUserA.get(i);
            for(int j = 0; j<ratingsByUserB.size(); j++){
                Rating ratingByB = ratingsByUserB.get(j);
                if (ratingByA.getMovieId()==ratingByB.getMovieId()){
                    this.matrix[ratingByA.getRating() - 1][ratingByB.getRating() - 1]++;
                }
            }
        }
        logMatrix();
    }

    public void calcTotalCount(){
        for (int i = 0; i < this.matrix.length; i++){
            for(int j = 0; j<this.matrix.length; j++){
                this.totalCount+=this.matrix[i][j];
            }
        }
        System.out.println("Total count: " + this.totalCount);
    }

    public void calcSimilarityCount(){
        for(int j = 0; j<this.matrix.length; j++){
            this.similarityCount+=this.matrix[j][j];
        }
        System.out.println("Sim count: " + this.similarityCount);
    }

    private void logMatrix() {
        for(int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
