package db;

import db.daologic.RatingDaoLogic;
import db.daologic.UserDaoLogic;
import models.Rating;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created with IntelliJ IDEA.
 * User: Alina
 * Date: 23.04.14
 * Time: 1:31
 * To change this template use File | Settings | File Templates.
 */
public class SimilarityMatrixManager {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static List<UserToUserRating> similarityMatrix = null;
    private static UserDaoLogic userDaoLogic = new UserDaoLogic(DbiManager.getDbi());
    private static RatingDaoLogic ratingDaoLogic = new RatingDaoLogic(DbiManager.getDbi());
    private static Cache cache = new Cache();


    private SimilarityMatrixManager() {
    }


    public List<UserToUserRating> getUsersFriends(User user, double similarityValue){
        List<UserToUserRating> friends = null;
        similarityMatrix = new Cache().getSimilarityMatrix();//TODO: define key
        if (similarityMatrix == null)
            return similarityMatrix;
        else {
        for(UserToUserRating userRating:similarityMatrix){
            if(userRating.isInRating(user) && userRating.getSimilarity()>=similarityValue){
                friends.add(userRating);
            }
        }
        }
        return friends;
    }

    public static List<UserToUserRating> CalculateUserSimilarity() {
        List<User> users = userDaoLogic.getAll();
        Map<User, List<Rating>> ratings = new HashMap<>();
        for(User user:users){
            ratings.put(user, ratingDaoLogic.getByUserId(users.indexOf(user)));
        }
        int usersCount = users.size();
        similarityMatrix = initUserToUserRatings(cache);
        RatingCountMatrix rcm;
        for (int u = 0; u < usersCount; u++) {
            for (int v = u + 1; v < usersCount; v++) {
                rcm = new RatingCountMatrix(ratings.get(users.get(u)), ratings.get(users.get(u)));
                int totalCount = rcm.GetTotalCount();
                int agreementCount = rcm.GetAgreementCount();
                if (agreementCount > 0) {
                    similarityMatrix.add(new UserToUserRating(new Tuple<>(users.get(u), users.get(v)), (double) agreementCount / (double) totalCount));
                } else {
                    similarityMatrix.add(new UserToUserRating(new Tuple<>(users.get(u), users.get(v)), 0.0));
                }
            }
            similarityMatrix.add(new UserToUserRating(new Tuple<>(users.get(u), users.get(u)), 1.0));
        }
        return similarityMatrix;
    }

    private static List<UserToUserRating> initUserToUserRatings(Cache jcs) {
        List<UserToUserRating> similarityMatrix;
        similarityMatrix =  jcs.getSimilarityMatrix(); //TODO: define key
        if(similarityMatrix==null){
            CalculateUserSimilarity();
            jcs.setSimilarityMatrix(similarityMatrix);
        }
        return similarityMatrix;
    }
}
