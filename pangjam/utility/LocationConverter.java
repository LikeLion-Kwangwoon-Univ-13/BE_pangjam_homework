package like_lion.pangjam.utility;

import like_lion.pangjam.domain.Location;
import like_lion.pangjam.dto.location.LocationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LocationConverter {

    public static LocationResponseDto toLocationResponseDto(List<Location> locations) {
        return LocationResponseDto.builder()
                .locations(locations.stream()
                        .map(LocationResponseDto.LocationInfo::from)
                        .collect(Collectors.toList()))
                .build();
    }

    public static LocationResponseDto.LocationInfo toLocationInfo(Location location) {
        return LocationResponseDto.LocationInfo.builder()
                .locationId(location.getLocationId())
                .name(location.getName())
                .category(location.getCategory())
                .averageRating(location.getAverageRating())
                .build();
    }
}