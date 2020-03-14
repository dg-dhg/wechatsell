package com.alexur.sell.dao.po;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SellerInfo")
public class SellerInfoPO{
    @Id
    @GeneratedValue
    @Column(length = 32,nullable = false)
    private long id;
    @Column(length = 32,nullable = false)
    private String sellerName;
    @Column(length = 32,nullable = false)
    private String password;
    @Column(length = 64,nullable = false)
    private String openId;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp updateTime;
}
