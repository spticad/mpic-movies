package algo;

/**
 * Created with IntelliJ IDEA.
 * User: Alina
 * Date: 16.04.14
 * Time: 1:36
 * To change this template use File | Settings | File Templates.
 */
public class RatedObject implements Comparable<RatedObject> {
    public Object getObject() {
        return object;
    }

    public double getObjectRating() {
        return objectRating;
    }

    private Object object;
    private double objectRating;

    public RatedObject(Object object, double objectRating) {
        this.object = object;
        this.objectRating = objectRating;
    }

    public int compareTo(RatedObject Object) {
        return (int) (Object.getObjectRating() - this.objectRating) * 10;
    }
}
