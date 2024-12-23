package org.example.springbootpractice.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.menu.request.MenuCategoryRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuCategoryResponseDto;
import org.example.springbootpractice.entity.MenuCategory;
import org.example.springbootpractice.repository.MenuCategoryRepository;
import org.example.springbootpractice.service.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuCategoryServiceImpl implements MenuCategoryService {

    @Autowired
    private final MenuCategoryRepository menuCategoryRepository;


    @Override
    public ResponseDto<List<MenuCategoryResponseDto>> getAllMenuCategory() {
        List<MenuCategoryResponseDto> data = null;

        try {
            List<MenuCategory> categories = menuCategoryRepository.findAll();
            data = new ArrayList<>();

            for(MenuCategory category : categories) {
                MenuCategoryResponseDto dto = new MenuCategoryResponseDto();
                dto.setId(category.getId());
                dto.setMenuCategory(category.getMenuCategory());
                dto.setMenuCategorySequence(category.getMenuCategorySequence());
                data.add(dto);
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<List<MenuCategoryResponseDto>> updateSequenceCategory(MenuCategoryRequestDto dto) {
        List<MenuCategoryResponseDto> data = null;
        try {
            MenuCategory menuCategory = menuCategoryRepository.findById(dto.getId())
                    .orElseThrow(() -> new Exception(ResponseMessage.DATABASE_ERROR));

            int oldSequence = menuCategory.getMenuCategorySequence();
            int newSequence = dto.getMenuCategorySequence();

            if(oldSequence != newSequence) {
                menuCategory.setMenuCategorySequence(Integer.MAX_VALUE);
                menuCategoryRepository.save(menuCategory);


                if (oldSequence < newSequence) {
                    List<MenuCategory> categories = menuCategoryRepository.findByMenuCategorySequenceBetween(oldSequence + 1, newSequence);
                    for (MenuCategory category : categories) {
                        category.setMenuCategorySequence(category.getMenuCategorySequence() - 1);
                        menuCategoryRepository.save(menuCategory);
                    }
                } else {
                    List<MenuCategory> categories = menuCategoryRepository.findByMenuCategorySequenceBetween(newSequence, oldSequence - 1);
                    for (MenuCategory category : categories) {
                        category.setMenuCategorySequence(category.getMenuCategorySequence() + 1);
                        menuCategoryRepository.save(menuCategory);
                    }
                }

                menuCategory.setMenuCategorySequence(newSequence);
                menuCategoryRepository.save(menuCategory);
            }

            List<MenuCategory> categories = menuCategoryRepository.findAllByOrderByMenuCategorySequenceAsc();
            data = new ArrayList<>();

            for (MenuCategory category : categories) {
                MenuCategoryResponseDto responseDto = new MenuCategoryResponseDto();
                responseDto.setId(category.getId());
                responseDto.setMenuCategory(category.getMenuCategory());
                responseDto.setMenuCategorySequence(category.getMenuCategorySequence());
                data.add(responseDto);
            }

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
}
