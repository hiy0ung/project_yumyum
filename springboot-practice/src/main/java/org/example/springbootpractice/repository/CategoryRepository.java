package org.example.springbootpractice.repository;

import org.example.springbootpractice.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<MenuCategory, Long> {

    List<MenuCategory> getMenuCategoryById(Long id);

}