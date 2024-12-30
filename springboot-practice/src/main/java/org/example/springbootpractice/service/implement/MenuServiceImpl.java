package org.example.springbootpractice.service.implement;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springbootpractice.common.constant.ResponseMessage;
import org.example.springbootpractice.dto.ResponseDto;
import org.example.springbootpractice.dto.menu.request.MenuOptionRequestDto;
import org.example.springbootpractice.dto.menu.request.MenuRequestDto;
import org.example.springbootpractice.dto.menu.response.MenuGetResponseDto;
import org.example.springbootpractice.dto.menu.response.MenuOptionDetailGetResponseDto;
import org.example.springbootpractice.dto.menu.response.MenuOptionGetResponseDto;
import org.example.springbootpractice.dto.menu.response.MenuResponseDto;
import org.example.springbootpractice.entity.Menu;
import org.example.springbootpractice.entity.MenuCategory;
import org.example.springbootpractice.entity.Store;
import org.example.springbootpractice.repository.MenuCategoryRepository;
import org.example.springbootpractice.repository.MenuRepository;
import org.example.springbootpractice.repository.StoreRepository;
import org.example.springbootpractice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private final MenuRepository menuRepository;
    @Autowired
    private final MenuCategoryRepository menuCategoryRepository;

    @Autowired
    MenuOptionServiceImpl menuOptionService;

    @Autowired
    private final StoreRepository storeRepository;

    public ResponseDto<MenuResponseDto> addMenu(MenuRequestDto dto, Long id) {
        MenuResponseDto data = null;

        try {
            Optional<MenuCategory> OptionalCategory = menuCategoryRepository.findById(dto.getCategoryId());
            if (OptionalCategory.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            Store store = storeRepository.findById(id).orElseThrow(() -> new RuntimeException("오류"));

            MenuCategory category = OptionalCategory.get();
            Menu menu = Menu.builder()
                    .menuName(dto.getMenuName())
                    .imageUrl(dto.getImageUrl())
                    .menuDescription(dto.getMenuDescription())
                    .menuPrice(dto.getMenuPrice())
                    .isAvailable(dto.getIsAvailable())
                    .menuCategory(category)
                    .store(store)
                    .build();
            Menu savedMenu = menuRepository.save(menu);

            List<MenuOptionRequestDto> options = dto.getMenuOption();
            if (options != null) {
                for(MenuOptionRequestDto optionDto : options) {
                    optionDto.setMenuId(savedMenu.getId());
                    menuOptionService.addMenuOption(optionDto, id);
                }
            }

            MenuResponseDto responseDto = new MenuResponseDto(savedMenu);
            data = responseDto;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<List<MenuGetResponseDto>> getAllMenus(Long id) {
        List<MenuGetResponseDto> data = null;
        try {
            data = menuRepository.findAllMenuWithCategoryAndOption(id).stream().collect(Collectors.groupingBy(
                    a -> (Long) a[0],
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        b -> {
                            List<MenuOptionGetResponseDto> optionAllResponseDtos = b.stream().collect(Collectors.groupingBy(
                                    c -> (Long) c[7],
                                    Collectors.collectingAndThen(
                                            Collectors.toList(),
                                            d -> {
                                                List<MenuOptionDetailGetResponseDto> optionDetailAllResponseDtos = d.stream().collect(Collectors.groupingBy(
                                                        e -> (Long) e[9],
                                                        Collectors.collectingAndThen(
                                                                Collectors.toList(),
                                                                f -> new MenuOptionDetailGetResponseDto((Long)f.get(0)[9], (String)f.get(0)[10], (Integer)f.get(0)[11])
                                                        )
                                                )).values().stream().collect(Collectors.toList());
                                                return new MenuOptionGetResponseDto((Long)d.get(0)[7], (String)d.get(0)[8], optionDetailAllResponseDtos);
                                            }
                                    )
                            )).values().stream().collect(Collectors.toList());
                            return new MenuGetResponseDto(
                                    (Long)b.get(0)[0],
                                    (String)b.get(0)[1],
                                    (Integer) b.get(0)[2],
                                    (String) b.get(0)[3],
                                    (String) b.get(0)[4],
                                    (Boolean) b.get(0)[5],
                                    (String) b.get(0)[6],
                                    optionAllResponseDtos);
                        }
                )
            )).values().stream().collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);

        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    @Override
    public ResponseDto<MenuGetResponseDto> getMenusById(Long menuId, Long id) {
        MenuGetResponseDto data = null;

        try {
            List<Object[]> result = menuRepository.findMenuWithCategoryAndOptionByMenuId(menuId);

            List<MenuOptionGetResponseDto> optionGetResponseDtos = result.stream().collect(Collectors.groupingBy(
                    a -> (Long) a[7],
                    Collectors.collectingAndThen(
                            Collectors.toList(),
                            b -> {
                                List<MenuOptionDetailGetResponseDto> optionDetailGetResponseDtos = b.stream()
                                        .map(c -> new MenuOptionDetailGetResponseDto(
                                                (Long) c[9],
                                                (String) c[10],
                                                (Integer) c[11]
                                        ))
                                        .collect(Collectors.toList());

                                return new MenuOptionGetResponseDto(
                                        (Long) b.get(0)[7],
                                        (String) b.get(0)[8],
                                        optionDetailGetResponseDtos
                                );
                            }
                    )
            )).values().stream().collect(Collectors.toList());
            data = new MenuGetResponseDto(
                    (Long) result.get(0)[0],
                    (String) result.get(0)[1],
                    (Integer) result.get(0)[2],
                    (String) result.get(0)[3],
                    (String) result.get(0)[4],
                    (Boolean) result.get(0)[5],
                    (String) result.get(0)[6],
                    optionGetResponseDtos
            );

        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<MenuResponseDto> updateMenu(@Valid Long menuId, MenuRequestDto dto, Long id) {
        MenuResponseDto data = null;

        try {
            Optional<MenuCategory> OptionalCategory = menuCategoryRepository.findById(dto.getCategoryId());
            if (OptionalCategory.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            MenuCategory category = OptionalCategory.get();

            Optional<Menu> OptionalMenu = menuRepository.findById(menuId);
            if (OptionalMenu.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            Menu menu = OptionalMenu.get();
            menu.setImageUrl(dto.getImageUrl());
            menu.setMenuName(dto.getMenuName());
            menu.setMenuDescription(dto.getMenuDescription());
            menu.setMenuPrice(dto.getMenuPrice());
            menu.setIsAvailable(dto.getIsAvailable());
            menu.setMenuCategory(category);
            menuRepository.save(menu);
            data = new MenuResponseDto(menu);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
    }

    public ResponseDto<Void> deleteMenu(Long menuId, Long id) {
        try {
            Optional<Menu> optionalMenu = menuRepository.findById(menuId);
            if (optionalMenu.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }
            menuRepository.deleteById(menuId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
    }
}

