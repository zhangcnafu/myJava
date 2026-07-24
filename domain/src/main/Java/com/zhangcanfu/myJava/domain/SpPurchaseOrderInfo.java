package com.zhangcanfu.myJava.domain;

import java.math.BigDecimal;
import java.time.Instant;

public class SpPurchaseOrderInfo {

    private Long orderId;
    private String siteId;
    private String dealerPartition;
    private String orderNo;
    private String orderDate;
    private Instant orderDatetime;
    private String orderStatus;
    private Long entryPicId;
    private Long entryFacilityId;
    private Long deliveryFacilityId;
    private String deliveryPlanDate;

    private BigDecimal taxRate = BigDecimal.ZERO;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getDealerPartition() {
        return dealerPartition;
    }

    public void setDealerPartition(String dealerPartition) {
        this.dealerPartition = dealerPartition;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Instant getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(Instant orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getEntryFacilityId() {
        return entryFacilityId;
    }

    public void setEntryFacilityId(Long entryFacilityId) {
        this.entryFacilityId = entryFacilityId;
    }

    public Long getDeliveryFacilityId() {
        return deliveryFacilityId;
    }

    public void setDeliveryFacilityId(Long deliveryFacilityId) {
        this.deliveryFacilityId = deliveryFacilityId;
    }

    public Long getEntryPicId() {
        return entryPicId;
    }

    public void setEntryPicId(Long entryPicId) {
        this.entryPicId = entryPicId;
    }

    public String getDeliveryPlanDate() {
        return deliveryPlanDate;
    }

    public void setDeliveryPlanDate(String deliveryPlanDate) {
        this.deliveryPlanDate = deliveryPlanDate;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
}
