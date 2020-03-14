package com.alexur.sell.dao;

import com.alexur.sell.dao.po.SellerInfoPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoRepositoryTest{


    @Autowired
    SellerInfoRepository repository;
    @Autowired
    Optional<SellerInfoPO> optional;

    private SellerInfoPO sellerInfoPO = new SellerInfoPO();


    @Test
    public void findSellerById() {
        optional = repository.findById(4L);
        if(optional.isPresent()) {
            sellerInfoPO = optional.get();
            log.info(sellerInfoPO.toString());
        } else {
            log.info("no such data");
        }
    }

    @Test
    public void findSellerIdLikeAnd() {

        List<SellerInfoPO> list = repository.findBySellerNameLike("%王%");
        if(list.isEmpty())
            System.out.println("no such data or statement mistake");
        Iterator<SellerInfoPO> iterator = list.iterator();
        for(; iterator.hasNext(); ) {
            log.info(iterator.next().toString());
        }
    }

    @Test
    public void findAll() {
        List<SellerInfoPO> list = repository.findAll();
        for(Iterator<SellerInfoPO> iterator = list.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }


    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(false)
    public void save() {
        sellerInfoPO = SellerInfoPO.builder().password("asdh18").openId("asdhj4").createTime(new Timestamp(System.currentTimeMillis())).updateTime(new Timestamp(System.currentTimeMillis())).sellerName("王9").build();
        repository.save(sellerInfoPO);
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    @Rollback(false)
    public void saveAll() {
        List<SellerInfoPO> list = new ArrayList();
        list.add(SellerInfoPO.builder().password("asdh11").openId("asdhj1").createTime(new Timestamp(System.currentTimeMillis())).updateTime(new Timestamp(System.currentTimeMillis())).sellerName("王五").build());
        list.add(SellerInfoPO.builder().password("asdh12").openId("asdhj2").createTime(new Timestamp(System.currentTimeMillis())).updateTime(new Timestamp(System.currentTimeMillis())).sellerName("王二").build());
        list.add(SellerInfoPO.builder().password("asdh13").openId("asdhj3").createTime(new Timestamp(System.currentTimeMillis())).updateTime(new Timestamp(System.currentTimeMillis())).sellerName("王六").build());
        list.add(SellerInfoPO.builder().password("asdh14").openId("asdhj4").createTime(new Timestamp(System.currentTimeMillis())).updateTime(new Timestamp(System.currentTimeMillis())).sellerName("王七").build());
        repository.saveAll(list);


    }

    @Test
    @Rollback(false)
    @Transactional(rollbackFor = Exception.class)
    public void updateSellerInfo() {
        if(repository.findById(4L).isPresent()) {
            sellerInfoPO = repository.findById(4L).get();
            sellerInfoPO.setOpenId("这是微信的openid");
            sellerInfoPO.setPassword("password");
            repository.save(sellerInfoPO);
        } else {
            log.info("no such data");
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void delete() {
        repository.deleteById(4L);
        optional = repository.findById(4L);
        if(optional.isPresent()) {
            log.info("删除失败");
        } else {
            log.info("删除成功");
        }
    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateSellerNameById() {
        repository.setSellerNameById("老五", 5L);
        if("老五".equals(repository.findById(5L).get().getSellerName())) {
            log.info("更新成功");
        } else {
            log.info("更新失败");
        }
    }

    @Test
    @Rollback(false)
    @Transactional
    public void pagingAndSorting() {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = Sort.by(order);
        /*pageNum是页码的意思？所以它是每次进行分页查询并不会预加载只会加载某一区间的数据*/
        Pageable pageable = PageRequest.of(0, 2, sort);
        Page<SellerInfoPO> pos = repository.findByIdPageAndSort(2L, pageable);
        /*System.out.println(pos.getTotalElements());
        Iterator<SellerInfoPO> iterator = pos.iterator();
        for(; iterator.hasNext(); ) {
            log.info(iterator.next().toString()
            );
        }*/
        List<SellerInfoPO> list= pos.getContent();
        for(SellerInfoPO infoPO:list){
            System.out.println(infoPO);
        }
    }


}