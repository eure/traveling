package com.eure.traveling;


import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;


@Table(name = "Shots")
public class Shots extends Model {
    public static final String TAG = Shots.class.getSimpleName();

    @Column(name = "Shots_Id")
    public int shotsId;

    @Column(name = "Category")
    public String category;

    @Column(name = "Title")
    public String title;

    @Column(name = "Image_Url")
    public String imageUrl;

    @Column(name = "Image_Teaser_Url")
    public String imageTeaserUrl;

    @Column(name = "Player_Name")
    public String playerName;

    @Column(name = "Likes_Count")
    public int likesCount;

    public int getShotsId() {
        return shotsId;
    }

    public void setShotsId(int shotsId) {
        this.shotsId = shotsId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageTeaserUrl() {
        return imageTeaserUrl;
    }

    public void setImageTeaserUrl(String imageTeaserUrl) {
        this.imageTeaserUrl = imageTeaserUrl;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public Shots() {
        super();
    }

    public static Shots getShots(int shotsId) {
        return new Select()
                .from(Shots.class)
                .where("Shots_Id = ?", shotsId)
                .executeSingle();
    }

    public static List<Shots> getCategoryList(String category, int offset) {
        Log.i(TAG, "getCategoryList");
        Log.i(TAG, "category = " + category);
        return new Select()
                .from(Shots.class)
                .where("Category = ?", category)
                .orderBy("Shots_Id ASC")
                .limit(15).offset(offset)
                .execute();
    }

    public static List<Shots> getCategoryList(String category) {
        Log.i(TAG, "getCategoryList");
        Log.i(TAG, "category = " + category);
        return new Select()
                .from(Shots.class)
                .where("Category = ?", category)
                .orderBy("Shots_Id ASC")
                .execute();
    }

}
