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
                        categoryEq(searchPlaceConditionInfo.getCategory()));


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
}
