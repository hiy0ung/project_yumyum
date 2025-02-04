package org.example.springbootpractice.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.menu.request.MenuOptionDetailRequestDto;
import org.example.springbootpractice.dto.menu.request.MenuOptionRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuOptionResponseDto;
import org.example.springbootpractice.dto.ResponseDto;

import org.example.springbootpractice.entity.Menu;
import org.example.springbootpractice.entity.MenuOption;
import org.example.springbootpractice.entity.MenuOptionGroup;
import org.example.springbootpractice.repository.MenuOptionGroupRepository;
import org.example.springbootpractice.repository.MenuOptionRepository;
import org.example.springbootpractice.repository.MenuRepository;
import org.example.springbootpractice.service.MenuOptionDetailService;
import org.example.springbootpractice.service.MenuOptionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuOptionServiceImpl implements MenuOptionService {

    private final MenuOptionRepository menuOptionRepository;

    private final MenuRepository menuRepository;

    private final MenuOptionGroupRepository menuOptionGroupRepository;

    private final MenuOptionDetailService menuOptionDetailService;

    @Override
    public ResponseDto<MenuOptionResponseDto> addMenuOption(MenuOptionRequestDto dto, Long id) {
        MenuOptionResponseDto data = null;

        try {
            Menu menu = menuRepository.findById(dto.getMenuId())
                    .orElseThrow(() -> new Error(ResponseMessage.NOT_EXIST_DATA));

            MenuOption menuOption = MenuOption.builder()
                    .optionName(dto.getOptionName())
                    .build();

            MenuOption savedMenuOption = menuOptionRepository.save(menuOption);
            List<MenuOptionDetailRequestDto> details = dto.getOptionDetail();
            if(details != null) {
                for (MenuOptionDetailRequestDto detailDto : details) {
                    detailDto.setMenuOptionId(savedMenuOption.getId());
                    menuOptionDetailService.addOptionDetail(detailDto, id);
                }
            }
            MenuOptionGroup menuOptionGroup = MenuOptionGroup.builder()
                    .menu(menu)
                    .menuOption(menuOption)
                    .build();

            menuOptionGroupRepository.save(menuOptionGroup);

            data = new MenuOptionResponseDto(savedMenuOption);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }


    @Override
    public ResponseDto<MenuOptionResponseDto> updateMenuOption(MenuOptionRequestDto dto, Long optionId, Long id) {
        MenuOptionResponseDto data = null;

        try {
            Optional<MenuOption> menuOptionOptional = menuOptionRepository.findById(optionId);

            if (menuOptionOptional.isPresent()) {
                MenuOption menuOption = menuOptionOptional.get().toBuilder()
                     .optionName(dto.getOptionName())
                     .build();

                MenuOption updateOption = menuOptionRepository.save(menuOption);
                data = new MenuOptionResponseDto(updateOption);
            } else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteMenuOption(Long optionId, Long id) {
        try {
            Optional<MenuOption> menuOptionOptional = menuOptionRepository.findById(optionId);

            if (menuOptionOptional.isPresent()) {
                MenuOption menuOption = menuOptionOptional.get();
                menuOptionRepository.delete(menuOption);
            } else {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}
