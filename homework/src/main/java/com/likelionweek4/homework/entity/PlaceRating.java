package com.likelionweek4.homework.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class PlaceRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "place_id")
    private Place place;

    private int one;
    private int two;
    private int three;
    private int four;
    private int five;

    public PlaceRating(Place place) {
        this.place = place;
        one = 0;
        two = 0;
        three = 0;
        four = 0;
        five = 0;
    }

    public void updateRating(int rating) {
        if(rating == 1) one++;
        else if(rating == 2) two++;
        else if(rating == 3) three++;
        else if(rating == 4) four++;
        else if(rating == 5) five++;
    }
}
