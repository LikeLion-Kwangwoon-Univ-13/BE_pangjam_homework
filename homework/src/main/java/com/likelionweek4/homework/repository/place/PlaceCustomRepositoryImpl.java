package com.likelionweek4.homework.repository.place;

import com.likelionweek4.homework.dto.place.PlaceRequestDTO;
import com.likelionweek4.homework.entity.Place;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.likelionweek4.homework.entity.QPlace.place;
@Slf4j
@Repository
@RequiredArgsConstructor
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Place> findBySearchCondition(PlaceRequestDTO.SearchPlaceConditionInfo searchPlaceConditionInfo, Pageable pageable) {

        JPAQuery<Place> jpaQuery = queryFactory.selectFrom(place)
                .where(nameEq(searchPlaceConditionInfo.getName()),
                        categoryEq(searchPlaceConditionInfo.getCategory()),
                        categoryGroupEq(searchPlaceConditionInfo.getCategoryGroup()));

        if(searchPlaceConditionInfo.getIsRatingASC() == null && searchPlaceConditionInfo.getIsDistanceASC() == null) {
            jpaQuery = orderByRating(jpaQuery, false);
        }
        else {
            if (searchPlaceConditionInfo.getIsDistanceASC() != null)
                jpaQuery = orderByDistance(jpaQuery, searchPlaceConditionInfo.getIsDistanceASC());
            else
                jpaQuery = orderByRating(jpaQuery, searchPlaceConditionInfo.getIsRatingASC());
        }



        Long totalCount = jpaQuery.fetchCount();

        jpaQuery.offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Place> places = jpaQuery.fetch();
        return new PageImpl<>(places, pageable, totalCount);
    }

    private BooleanExpression nameEq(String name) {
        if(name != null) {
            return place.name.contains(name);
        }
        return null;
    }

    private BooleanExpression categoryEq(String category) {
        if(category != null) {
            return place.category.eq(category);
        }
        return null;
    }

    private BooleanExpression categoryGroupEq(String categoryGroup) {
        if(categoryGroup != null) {
            return place.categoryGroup.eq(categoryGroup);
        }
        return null;
    }

    private JPAQuery<Place> orderByDistance(JPAQuery<Place> query, Boolean isDistanceASC) {
        if(isDistanceASC == null || isDistanceASC) {
            return query.orderBy(place.distance.asc(), place.placeId.asc());
        }
        return query.orderBy(place.distance.desc(), place.placeId.asc());
    }

    private JPAQuery<Place> orderByRating(JPAQuery<Place> query, Boolean isRatingASC) {
        if(isRatingASC == null || isRatingASC) {
            return query.orderBy(place.rating.asc(), place.placeId.asc());
        }
        return query.orderBy(place.rating.desc(), place.placeId.asc());
    }
}
