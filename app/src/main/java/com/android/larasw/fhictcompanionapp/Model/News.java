package com.android.larasw.fhictcompanionapp.Model;

import android.graphics.Bitmap;

/**
 * Created by Res Non Verba on 11/10/2017.
 */

public class News {
    private Bitmap icon, newsImage;
    private String newsTitle, newsContent, newsLink, newsDate;

    public News(Bitmap icon, Bitmap newsImage, String newsTitle, String newsContent, String newsLink, String newsDate) {
        this.icon = icon;
        this.newsImage = newsImage;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.newsLink = newsLink;
        this.newsDate = newsDate;
    }

    public Bitmap getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(Bitmap newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
}
