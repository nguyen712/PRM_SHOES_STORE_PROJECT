package com.buikhoinguyen.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class BasedEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	
	@Column(nullable = true)
	private String name;
	
	@Column(nullable = true)
    @CreatedBy
    private String createdBy;

	@Column(nullable = true)
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(nullable = true)
    @LastModifiedBy
    private String modifiedBy;

    @Column(nullable = true)
    @LastModifiedDate
    private LocalDateTime modifiedDate;
    
    @Column
	private boolean status;
}
