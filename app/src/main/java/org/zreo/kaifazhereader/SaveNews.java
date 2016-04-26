package org.zreo.kaifazhereader;

/**
 * Created by 85789 on 2016/4/26.
 */
public class SaveNews {
    private int imageId;
    private String title;
    private String resource;
    private int itemCommentId;
    private int itemLikeId;
    private String itemCommentNumber;
    private String itemLikeNumber;
    public SaveNews(String title,int imageId,String resource,int itemCommentId,int itemLikeId,String itemCommentNumber,String itemLikeNumber) {
        this.imageId=imageId;
        this.title=title;
        this.resource=resource;
        this.itemCommentId=itemCommentId;
        this.itemLikeId=itemLikeId;
        this.itemCommentNumber=itemCommentNumber;
        this.itemLikeNumber=itemLikeNumber;
    }
    public int getImageId(){
        return imageId;
    }
    public String getTitle(){
        return title;
    }
    public String getResource(){
        return resource;
    }
    public int getItemCommentId(){return itemCommentId;}
    public int getItemLikeId(){return itemLikeId;}


  }
