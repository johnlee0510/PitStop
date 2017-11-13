package PitStop.Model;

/**
 * Created by John on 2017-11-04.
 */

public class FoodTruckMaximumLimitation {
    private String truckName;
    private int maxNum;

    public FoodTruckMaximumLimitation(String truckName, int maxNum) {
        this.truckName = truckName;
        this.maxNum = maxNum;
    }
    public String getTruckName() {
        return truckName;
    }

    public int getMaxNum() {
        return maxNum;
    }




}
