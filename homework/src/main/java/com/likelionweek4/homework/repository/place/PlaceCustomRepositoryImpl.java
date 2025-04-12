package com.likelionweek4.homework.repository.place;

import com.likelionweek4.homework.dto.place.PlaceRequestDTO;
import com.likelionweek4.homework.entity.Place;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.likelionweek4.homework.entity.QPlace.place;

@Repository
@RequiredArgsConstructor
public class PlaceCustomRepositoryImpl implements PlaceCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Place> findBySearchCondition(PlaceRequestDTO.SearchPlaceConditionInfo searchPlaceConditionInfo) {
        return orderByDistance(
                    queryFactory.selectFrom(place)
                    .where(nameEq(searchPlaceConditionInfo.getName()),
                            categoryEq(searchPlaceConditionInfo.getCategory()),
                            categoryGroupEq(searchPlaceConditionInfo.getCategoryGroup())),
                    searchPlaceConditionInfo.getDistance()
                ).fetch();
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

    private JPAQuery<Place> orderByDistance(JPAQuery<Place> query, Boolean distance) {
        if(distance == null || distance) {
            return query.orderBy(place.distance.asc());
        }
        return query.orderBy(place.distance.desc());
    }
}
