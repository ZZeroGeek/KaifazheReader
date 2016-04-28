package org.zreo.kaifazhereader;

/**
 * Created by 85789 on 2016/4/27.
 */
public class SaveLandWay {
    private String landWay;
    private int landImage;

    public SaveLandWay(String landWay,int landImage) {
        this.landImage=landImage;
        this.landWay=landWay;
    }
    public String getLandWay(){
        return landWay;
    }
    public int getLandImage(){
        return landImage;
    }
}
