package org.example.springbootpractice.repository;

import org.example.springbootpractice.entity.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
    List<MenuCategory> findByMenuCategorySequenceBetween(int start, int end);
    List<MenuCategory> findAllByOrderByMenuCategorySequenceAsc();
}
