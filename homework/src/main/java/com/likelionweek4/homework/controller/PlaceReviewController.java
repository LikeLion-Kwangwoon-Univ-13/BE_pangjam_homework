package com.likelionweek4.homework.controller;

import com.likelionweek4.homework.dto.placereview.PlaceReviewRequestDTO;
import com.likelionweek4.homework.dto.placereview.PlaceReviewResponseDTO;
import com.likelionweek4.homework.service.PlaceReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/placeReviews")
@RequiredArgsConstructor
public class PlaceReviewController {
    private final PlaceReviewService placeReviewService;

    @PostMapping("/{placeId}")
    public ResponseEntity<PlaceReviewResponseDTO.ReviewInfo> createReview(
            @PathVariable("placeId") Long placeId,
            @RequestBody PlaceReviewRequestDTO.CreateReviewInfo requestDTO) {
        requestDTO.setPlaceId(placeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(placeReviewService.create(requestDTO));
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<PlaceReviewResponseDTO.SearchReviewsResult> searchReviews(
            @PathVariable("page") int page,
            @ModelAttribute PlaceReviewRequestDTO.SearchReviewsInfo requestDTO) {
        requestDTO.setPage(page);
        return ResponseEntity.status(HttpStatus.OK).body(placeReviewService.searchReviewByPlaceId(requestDTO));
    }

}
