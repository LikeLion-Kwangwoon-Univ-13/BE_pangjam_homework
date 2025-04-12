package com.likelionweek4.homework.repository.place;

import com.likelionweek4.homework.dto.place.PlaceRequestDTO;
import com.likelionweek4.homework.entity.Place;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.likelionweek4.homework.entity.QPlace.place;
@Slf4j
@Repository
@RequiredArgsConstructor
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Place> findBySearchCondition(PlaceRequestDTO.SearchPlaceConditionInfo searchPlaceConditionInfo) {
        log.error("Search place condition: {}", searchPlaceConditionInfo.getIsRatingASC());

        JPAQuery<Place> jpaQuery = queryFactory.selectFrom(place)
                .where(nameEq(searchPlaceConditionInfo.getName()),
                        categoryEq(searchPlaceConditionInfo.getCategory()),
                        categoryGroupEq(searchPlaceConditionInfo.getCategoryGroup()));

        if(searchPlaceConditionInfo.getIsRatingASC() != null)
            jpaQuery = orderByRating(jpaQuery, searchPlaceConditionInfo.getIsRatingASC());

        if(searchPlaceConditionInfo.getIsDistanceASC() != null)
            jpaQuery = orderByDistance(jpaQuery, searchPlaceConditionInfo.getIsDistanceASC());

        return jpaQuery.fetch();
    }

    private BooleanExpression nameEq(String name) {
        if(name != null) {
            return place.name.contains(name);
        }
        return null;
    }

    private BooleanExpression categoryEq(String category) {
        if(category != null) {
            return place.name.contains(category);
        }
        return null;
    }

    private BooleanExpression categoryGroupEq(String categoryGroup) {
        if(categoryGroup != null) {
            return place.name.contains(categoryGroup);
        }
        return null;
    }

    private JPAQuery<Place> orderByDistance(JPAQuery<Place> query, Boolean isDistanceASC) {
        if(isDistanceASC == null || isDistanceASC) {
            return query.orderBy(place.distance.asc());
        }
        return query.orderBy(place.distance.desc());
    }

    private JPAQuery<Place> orderByRating(JPAQuery<Place> query, Boolean isRatingASC) {
        if(isRatingASC == null || isRatingASC) {
            return query.orderBy(place.rating.asc());
        }
        return query.orderBy(place.rating.desc());
    }
}
