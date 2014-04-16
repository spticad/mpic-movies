package algo;

import models.Rating;
import models.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Alina
 * Date: 12.04.14
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class RatingCountMatrix implements Serializable {
    private int[][] matrix = null;

    public RatingCountMatrix(List<Rating> ratingsByUserA, List<Rating> ratingsByUserB, int nRatingValues) {
        Init(nRatingValues);
        Calculate(ratingsByUserA, ratingsByUserB);
    }

    private void Calculate(List<Rating> ratingsByUserA, List<Rating> ratingsByUserB) {
        for (int rA = 0; rA < ratingsByUserA.size(); rA++) {
            for (int rB = 0; rB < ratingsByUserB.size(); rB++) {
                if (ratingsByUserA.get(rA).getMovieId() == ratingsByUserB.get(rB).getMovieId()) //if user B rated the movie rated by A
                    matrix[rA][rB]++;
            }
        }
    }

    private void Init(int nSize) {
        matrix = new int[nSize][nSize];
    }

    public int GetTotalCount() {
        int totalCount = 0;
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                totalCount += matrix[i][j];
            }
        }
        return totalCount;
    }

    public int GetAgreementCount() {
        int agreementCount = 0;
        for (int i = 0; i < matrix.length; i++)
            agreementCount += matrix[i][i];
        return agreementCount;
    }
}