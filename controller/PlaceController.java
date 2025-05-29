package like_lion.pangjam.controller;

import like_lion.pangjam.dto.global.SliceResponseDto;
import like_lion.pangjam.dto.place.PlaceResponseDto;
import like_lion.pangjam.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    // place 조회
    @GetMapping
    public ResponseEntity<SliceResponseDto<PlaceResponseDto.PlaceInfo>> getPlaces(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @PageableDefault(size = 15) Pageable pageable
    ) {
        Slice<PlaceResponseDto.PlaceInfo> slice = placeService.getPlaces(name, category, pageable);

        SliceResponseDto<PlaceResponseDto.PlaceInfo> response = SliceResponseDto.of(
                slice.getContent(),
                slice.hasNext(),
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        return ResponseEntity.ok(response);
    }

    // place 상세 조회
    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceResponseDto.Detail> getPlaceDetail(@PathVariable long placeId) {
        PlaceResponseDto.Detail placeDetail = placeService.getPlaceDetail(placeId);
        return ResponseEntity.ok(placeDetail);
    }
}
