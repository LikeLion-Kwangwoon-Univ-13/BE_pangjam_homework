package like_lion.pangjam.controller;

import like_lion.pangjam.dto.location.LocationResponseDto;
import like_lion.pangjam.dto.global.SliceResponseDto;
import like_lion.pangjam.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    // location 생성
    @PostMapping
    public ResponseEntity<LocationResponseDto.LocationInfo> createLocation(
            @RequestBody LocationResponseDto.LocationInfo locationDto) {

        LocationResponseDto.LocationInfo createdLocation = locationService.createLocation(locationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
    }


    @GetMapping
    public ResponseEntity<SliceResponseDto<LocationResponseDto.LocationInfo>> getLocations(
            @RequestParam(required = false) Integer lastLocationId
    ) {
        Slice<LocationResponseDto.LocationInfo> slice = locationService.getAllLocation(lastLocationId);

        SliceResponseDto<LocationResponseDto.LocationInfo> response = SliceResponseDto.of(
                slice.getContent(),
                slice.hasNext(),
                0,
                15
        );

        return ResponseEntity.ok(response);
    }

    //카테고리별 조회
    @GetMapping("/category/{category}")
    public ResponseEntity<LocationResponseDto> getLocationByCategory(@PathVariable String category)
    {
        LocationResponseDto locations = locationService.getLocationByCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body(locations);
    }

    //이름으로 조회
    @GetMapping("/name/{name}")
    public ResponseEntity<LocationResponseDto> getLocationByName(@PathVariable String name)
    {
        LocationResponseDto locations = locationService.getLocationByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(locations);
    }

    // 상세 정보 조회
    @GetMapping("/locationId/{locationId}")
    public ResponseEntity<LocationResponseDto.Detail> geLocationDetail(@PathVariable int locationId)
    {
        LocationResponseDto.Detail locationDetail =locationService.getLocationDetail(locationId);
        return ResponseEntity.status(HttpStatus.OK).body(locationDetail);
    }
}