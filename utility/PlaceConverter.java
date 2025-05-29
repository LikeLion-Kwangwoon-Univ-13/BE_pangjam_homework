package like_lion.pangjam.utility;

import like_lion.pangjam.domain.Place;
import like_lion.pangjam.dto.place.PlaceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PlaceConverter {

    public static PlaceResponseDto toPlaceResponseDto(List<Place> places) {
        return PlaceResponseDto.builder()
                .places(places.stream()
                        .map(PlaceResponseDto.PlaceInfo::from)
                        .collect(Collectors.toList()))
                .build();
    }

    public static PlaceResponseDto.PlaceInfo toPlaceInfo(Place place) {
        return PlaceResponseDto.PlaceInfo.builder()
                .placeId(place.getPlaceId())
                .name(place.getName())
                .category(place.getCategory())
                .averageRating(place.getAverageRating())
                .build();
    }
}