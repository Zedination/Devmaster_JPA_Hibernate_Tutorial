package com.devmaster.repository;

import com.devmaster.entity.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Khai báo một interface extends từ JpaRepository hoặc CrudRepository
 * Chỉ định kiểu dữ liệu của Entity và kiểu dữ liệu của ID
 * Đánh dấu bằng @Repository để xác định đây là một repository (Có hoặc ko có đều đc, Spring sẽ tự hiểu và sẽ impl một bean từ interface này)
 */
//@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByName(String name);

    List<Person> findByNameAndAge(String name, Integer age);

    List<Person> findByAgeGreaterThan(Integer age);

    List<Person> findTop5ByAgeGreaterThanOrderByAge(Integer age);

    List<Person> findByNameContains(String keyword);

    List<Person> findByNameRemovedAccentEndingWith(String keyword);

    @Query("select p from Person p where p.id = :id")
    Optional<Person> timKiemTheoId(@Param("id") Long khoaChinh);

    @Query(nativeQuery = true, value = "select * from person")
    List<Person> getAllPerson();

    @Modifying
    @Transactional
    @Query("update Person p set p.createAt = :time")
    int updateCreateAtAllRecord(LocalDateTime time);

    @Query("from Person ")
    Slice<Person> getAllRecord(Pageable pageable);
}
