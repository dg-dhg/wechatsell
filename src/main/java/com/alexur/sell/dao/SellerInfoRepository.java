package com.alexur.sell.dao;

import com.alexur.sell.dao.po.SellerInfoPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerInfoRepository extends JpaRepository<SellerInfoPO, Long>{
    @Query("from SellerInfoPO as po where po.sellerName like ?1")
    List<SellerInfoPO> findBySellerNameLike(String name);

    @Query(" update SellerInfoPO po set po.sellerName = ?1 where po.id=?2 ")
    @Modifying
    void setSellerNameById(String sellerName, Long id);
    /*value = */
    /*, countQuery = "select count(po) from SellerInfoPO po where id >?1")*/
    @Query("from SellerInfoPO po where id>?1 ")
    Page<SellerInfoPO> findByIdPageAndSort(Long id,Pageable pageable);


    /*@Repository
    interface SellerInfoPagingAndSortingRepository extends PagingAndSortingRepository{


    }*/
}
