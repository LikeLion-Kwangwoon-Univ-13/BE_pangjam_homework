package like_lion.pangjam.service;

import like_lion.pangjam.domain.Location;
import like_lion.pangjam.domain.LocationReview;
import like_lion.pangjam.dto.location.LocationResponseDto;
import like_lion.pangjam.repository.LocationRepository;
import like_lion.pangjam.repository.LocationReviewRepository;
import like_lion.pangjam.utility.LocationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationReviewRepository locationReviewRepository;

    //전체 리스트 조회
public Slice<LocationResponseDto.LocationInfo> getAllLocation(Integer lastLocationId) {
    PageRequest pageRequest = PageRequest.of(0, 15, Sort.by("averageRating").descending());
    Slice<Location> locationSlice = locationRepository
            .findByLocationIdLessThan(
                    lastLocationId != null ? lastLocationId : Integer.MAX_VALUE,
                    pageRequest
            );
            
    return new SliceImpl<>(
            locationSlice.getContent().stream()
                    .map(LocationConverter::toLocationInfo)
                    .collect(Collectors.toList()),
            pageRequest,
            locationSlice.hasNext()
    );
}

    //카테고리별 식당 조회
    public LocationResponseDto getLocationByCategory(String category) {
        List<Location> locations = locationRepository.findAllByCategory(category);
        return LocationConverter.toLocationResponseDto(locations);
    }

    //이름으로 식당 조회
    public LocationResponseDto getLocationByName(String name) {
        List<Location> locations = locationRepository.findByNameContaining(name);
        return LocationConverter.toLocationResponseDto(locations);
    }

    //상세정보 조회
    public LocationResponseDto.Detail getLocationDetail(int locationId) {
        Location location = locationRepository.findByLocationId(locationId);

        List<LocationReview> locationReviews = locationReviewRepository.findTop5ByLocation_LocationIdOrderByCreatedAtDesc(locationId);

        return LocationResponseDto.Detail.from(location, locationReviews);
    }

    //식당 생성
    public LocationResponseDto.LocationInfo createLocation(LocationResponseDto.LocationInfo locationDto) {

        Location location = Location.builder()
                .name(locationDto.getName())
                .category(locationDto.getCategory())
                .phone(locationDto.getPhone())
                .build();

        Location saved = locationRepository.save(location);

        return LocationResponseDto.LocationInfo.builder()
                .locationId(saved.getLocationId())
                .name(saved.getName())
                .category(saved.getCategory())
                .averageRating(saved.getAverageRating())
                .phone(saved.getPhone())
                .build();
    }
}