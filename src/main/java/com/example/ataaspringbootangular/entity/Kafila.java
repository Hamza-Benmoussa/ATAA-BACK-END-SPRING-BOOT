package com.example.ataaspringbootangular.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "kafilas")
@EntityListeners(AuditingEntityListener.class)

public class Kafila {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomKfila;

    @ManyToOne
    private Dowar dowar;
    @ManyToOne
    private Association association;

    private Date dateDepart;

    private Date dateArrivee;

    private boolean arrivedKafila;

    @OneToMany(mappedBy = "kafila", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @Fetch(FetchMode.SUBSELECT)
    private List<BienKafila> bienKafilas;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;
    @Column(name = "modified_date", nullable = false, updatable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDate;
    @Column(name = "created_by", nullable = false, updatable = false)
    @CreatedBy
    private String createdBy;
    @Column(name = "modified_by", nullable = false, updatable = false)
    @LastModifiedBy
    private String modifiedBy;
    @Column(name="is_deleted")
    private boolean deleted;
}

