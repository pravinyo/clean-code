package com.c.refactoring.movie;

import com.c.refactoring.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Movie {

    public static final String RATING_TYPE_A = "A";
    public static final List<String> B_RATINGS_LIST = Arrays.asList("B1", "B2", "B3", "B4");
    String rating;

    public Movie(String rating) {
        super();
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public boolean isValidRating() {
        String rating = this.getRating();
        if (rating == null) return false;
        if (isValidBRating(rating)) return true;
        return isValidARating(rating);
    }

    private boolean isValidARating(String rating) {
        String firstChar = rating.substring(0, 1);

        return firstChar.equalsIgnoreCase(RATING_TYPE_A)
                && rating.length() == 3
                && StringUtils.isNumeric(rating.substring(1, 3));
    }

    private boolean isValidBRating(String rating) {
        return B_RATINGS_LIST.contains(rating);
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
