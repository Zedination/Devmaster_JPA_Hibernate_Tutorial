package com.devmaster.entity;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Data
@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "address")
	private String address;

	@Column(name = "name_removed_accent")
	private String nameRemovedAccent;

	@CreationTimestamp
	@Column(name = "create_at")
	private LocalDateTime createAt;

	@UpdateTimestamp
	@Column(name = "update_at")
	private LocalDateTime updateAt;

	@PrePersist
	private void prePersist() {
		this.setNameRemovedAccent(removeAccent(this.name));
	}
	
	@PreUpdate
	private void preUpdate() {
		this.setNameRemovedAccent(removeAccent(this.name));
	}


	private String removeAccent(String s) {
		s = s.toLowerCase();
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		temp = pattern.matcher(temp).replaceAll("");
		return temp.replaceAll("đ", "d");
	}
}
