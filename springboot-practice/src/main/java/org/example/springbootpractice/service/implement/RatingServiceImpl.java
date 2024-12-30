package org.example.springbootpractice.service.implement;

import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.rating.response.RatingMonthResponseDto;
import org.example.springbootpractice.dto.rating.response.RatingStatisticsResponseDto;
import org.example.springbootpractice.service.RatingService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final StoreRepository storeRepository;

    @Override
    public ResponseDto<List<RatingStatisticsResponseDto>> getReviewCountByRating(Long id) {
        List<RatingStatisticsResponseDto> data = null;
        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);

            if(optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Store store = optionalStore.get();

            List<Object[]> ratingStatistics = ratingRepository.findReviewCountByRating(store.getId());

            data = ratingStatistics.stream()
                    .map(dto -> new RatingStatisticsResponseDto((int) dto[0], (Long) dto[1]))
                    .collect(Collectors.toList());

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<RatingMonthResponseDto>> getAvgRatingByMonth(Long id, String date) {
        List<RatingMonthResponseDto> data = null;
        try {

            Optional<Store> optionalStore = storeRepository.getStoreByUserId(id);

            if (optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Store store = optionalStore.get();

            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
            LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);

            int startDate = localDateTime.getMonthValue() - 6;
            int endDate = localDateTime.getMonthValue();
            List<Object[]> ratingMonths = ratingRepository.findRatingAvgByMonth(store.getId(), startDate, endDate);

            data = ratingMonths.stream()
                    .map(dto -> new RatingMonthResponseDto((Integer) dto[0], (Integer) dto[1]))
                    .collect(Collectors.toList());

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }
}
