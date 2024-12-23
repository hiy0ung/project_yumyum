package org.example.springbootpractice.service.implement;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.user.request.UserRequestDto;
import org.example.springbootpractice.dto.user.response.UserResponseDto;
import org.example.springbootpractice.entity.User;
import org.example.springbootpractice.repository.MypageRepository;
import org.example.springbootpractice.service.MyPageService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final MypageRepository mypageRepository;
    @Override
    public ResponseDto<UserResponseDto> getAllInfo(Long id) {
        UserResponseDto data = null;

        try {
            User user = mypageRepository.findById(id)
                    .orElseThrow(() -> new Error(ResponseMessage.NOT_EXIST_USER));

            data = new UserResponseDto(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<UserResponseDto> updateUserInfo(Long id, UserRequestDto dto) {
        UserResponseDto data = null;

        try {
            User user = mypageRepository.findById(id)
                    .orElseThrow(() -> new Error(ResponseMessage.NOT_EXIST_USER));

            user = user.toBuilder()
                    .userPw(dto.getUserPw())
                    .userPhone(dto.getUserPhone())
                    .userEmail(dto.getUserEmail())
                    .marketingAgreed(dto.getMarketingAgreed())
                    .build();
            mypageRepository.save(user);
            data = new UserResponseDto(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteUserInfo(Long id) {
        try {
            User checkUser = mypageRepository.findById(id)
                    .orElseThrow(() -> new Error(ResponseMessage.NOT_EXIST_USER));

            mypageRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
