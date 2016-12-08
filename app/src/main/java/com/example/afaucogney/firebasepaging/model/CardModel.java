package com.example.afaucogney.firebasepaging.model;

import com.example.afaucogney.firebasepaging.common.StubUrlProvider;

/**
 * Created by afaucogney on 02/12/2016.
 */

public class CardModel {

    String name, comment;
    String photoPath, avatarPath;
    Integer status = -1, count = 0;

    //TODO use builder

    public CardModel() {
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static CardModel newRandomInstance(int count) {
        CardModel cm = new CardModel();

        cm.name = StubUrlProvider.getRandomString(10);
        cm.comment = "Comment " + StubUrlProvider.getRandomString(100);
        cm.avatarPath = StubUrlProvider.getAvatarUrl(cm.name);
        cm.photoPath = StubUrlProvider.getPhotoUrl(count);
        cm.count = count;
        return cm;
    }


    public static String getCategorie(CardModel model) {
        final String SPORT = "sport", FOOD = "food", PEOPLE = "people", CITY = "city", NATURE = "nature", OTHERS = "others";

        final String[] categories = {SPORT, FOOD, PEOPLE, CITY, NATURE};

        for (String cat : categories) {
            if (model.getPhotoPath().contains(cat))
                return cat;
        }
        return OTHERS;

    }
}
