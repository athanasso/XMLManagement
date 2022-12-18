package com.codehub.xmlmanagement.Model;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Statistics {
    private int paragraphs;
    private int sentences;
    private int words;
    private int distinctWords;
    private Date creationDate;
    private String author;
    private String appClassName;

    public Statistics(int paragraphs, int sentences, int words, int distinctWords, Date creationDate, String author, String appClassName) {
        this.paragraphs = paragraphs;
        this.sentences = sentences;
        this.words = words;
        this.distinctWords = distinctWords;
        this.creationDate = creationDate;
        this.author = author;
        this.appClassName = appClassName;
    }

    public Statistics() {
    }
    
    public int getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(int paragraphs) {
        this.paragraphs = paragraphs;
    }

    public int getSentences() {
        return sentences;
    }

    public void setSentences(int sentences) {
        this.sentences = sentences;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public int getDistinctWords() {
        return distinctWords;
    }

    public void setDistinctWords(int distinctWords) {
        this.distinctWords = distinctWords;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAppClassName() {
        return appClassName;
    }

    public void setAppClassName(String appClassName) {
        this.appClassName = appClassName;
    }

    @Override
    public String toString() {
        return "Statistics{" + "paragraphs=" + paragraphs + ", sentences=" + sentences + ", words=" + words + ", distinctWords=" + distinctWords + ", creationDate=" + creationDate + ", author=" + author + ", appClassName=" + appClassName + '}';
    }

}
