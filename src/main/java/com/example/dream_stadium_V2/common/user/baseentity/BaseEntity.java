package com.example.dream_stadium_V2.common.user.baseentity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 이 필드들을 자식에 물려 줌
@EntityListeners(AuditingEntityListener.class) //이 Entity에 Auditing 기능을 적용
@Getter
public class BaseEntity {

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime ModifiedTime;
}
