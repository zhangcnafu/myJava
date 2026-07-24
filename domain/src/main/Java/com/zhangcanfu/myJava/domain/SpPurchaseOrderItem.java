package com.zhangcanfu.myJava.domain;

import java.math.BigDecimal;

public class SpPurchaseOrderItem {

    private static final long serialVersionUID = 1L;
    private Long orderItemId;
    private String siteId;
    private String dealerPartition;
    private Long orderId;
    private String seqNo;
    private Long productId;

    private BigDecimal standardPrice = BigDecimal.ZERO;

    private BigDecimal discount = BigDecimal.ZERO;

    private BigDecimal price = BigDecimal.ZERO;

    private BigDecimal qty = BigDecimal.ZERO;

    private String boCancelFlag;
    private String cancelReasonTypeId;
    private String autoPoTarget;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(BigDecimal standardPrice) {
        this.standardPrice = standardPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getBoCancelFlag() {
        return boCancelFlag;
    }

    public void setBoCancelFlag(String boCancelFlag) {
        this.boCancelFlag = boCancelFlag;
    }

    public String getCancelReasonTypeId() {
        return cancelReasonTypeId;
    }

    public void setCancelReasonTypeId(String cancelReasonTypeId) {
        this.cancelReasonTypeId = cancelReasonTypeId;
    }

    public String getAutoPoTarget() {
        return autoPoTarget;
    }

    public void setAutoPoTarget(String autoPoTarget) {
        this.autoPoTarget = autoPoTarget;
    }
}
