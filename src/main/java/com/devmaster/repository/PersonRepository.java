package com.devmaster.repository;

import com.devmaster.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Khai báo một interface extends từ JpaRepository hoặc CrudRepository
 * Chỉ định kiểu dữ liệu của Entity và kiểu dữ liệu của ID
 * Đánh dấu bằng @Repository để xác định đây là một repository (Có hoặc ko có đều đc, Spring sẽ tự hiểu và sẽ impl một bean từ interface này)
 */
//@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
