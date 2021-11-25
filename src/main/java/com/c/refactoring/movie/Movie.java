package com.c.refactoring.movie;

import com.c.refactoring.StringUtils;

public class Movie {

    public static final String RATING_TYPE_B = "B";
    public static final String RATING_TYPE_A = "A";
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
        String firstChar = rating.substring(0, 1);
        String secondChar = rating.substring(1, 2);

        return firstChar.equalsIgnoreCase(RATING_TYPE_B)
                && (rating.length() == 2)
                && StringUtils.isNumeric(secondChar)
                && Integer.parseInt(secondChar) > 0
                && Integer.parseInt(secondChar) < 5;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
