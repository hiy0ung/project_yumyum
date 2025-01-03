package org.example.springbootpractice.service.implement;

import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.category.request.CreateCategoryRequestDto;
import org.example.springbootpractice.dto.category.request.UpdateCategoryRequestDto;
import org.example.springbootpractice.dto.category.response.CategoryResponseDto;
import org.example.springbootpractice.entity.MenuCategory;
import org.example.springbootpractice.entity.Store;
import org.example.springbootpractice.repository.CategoryRepository;
import org.example.springbootpractice.repository.StoreRepository;
import org.example.springbootpractice.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;

    @Override
    public ResponseDto<List<CategoryResponseDto>> getCategories(Long userId, @PathVariable Long id) {
        List<CategoryResponseDto> data = null;

        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(userId);

            if(optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Store store = optionalStore.get();

            List<MenuCategory> menuCategoryList = categoryRepository.getMenuCategoryById(id);

            data = menuCategoryList.stream()
                    .map(CategoryResponseDto::new)
                    .collect(Collectors.toList());

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<CategoryResponseDto> createCategory(Long userId, CreateCategoryRequestDto dto) {
        CategoryResponseDto data = null;

        try {
            Optional<Store> optionalStore = storeRepository.getStoreByUserId(userId);

            if(optionalStore.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_STORE);
            }

            Store store = optionalStore.get();

            MenuCategory menuCategory = MenuCategory.builder()
                    .menuCategory(dto.getCategoryName())
                    .build();

            categoryRepository.save(menuCategory);

            data = new CategoryResponseDto(menuCategory);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<CategoryResponseDto> updateCategory(UpdateCategoryRequestDto dto) {
        CategoryResponseDto data = null;
        Long CategoryId = dto.getId();

        try {

            Optional<MenuCategory> optionalMenuCategory = categoryRepository.findById(CategoryId);

            if(optionalMenuCategory.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CATEGORY);
            }

            MenuCategory menuCategory = optionalMenuCategory.get();
            MenuCategory updateMenuCategory = menuCategory.toBuilder()
                    .menuCategory(dto.getCategoryName())
                    .build();
            categoryRepository.save(updateMenuCategory);

            data = new CategoryResponseDto(updateMenuCategory);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<Void> deleteCategory(Long id) {
        try {
            Optional<MenuCategory> optionalMenuCategory = categoryRepository.findById(id);

            if(optionalMenuCategory.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_CATEGORY);
            }

            MenuCategory menuCategory = optionalMenuCategory.get();
            categoryRepository.delete(menuCategory);

        }catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}

