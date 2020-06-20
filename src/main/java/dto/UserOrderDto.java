package dto;

public class UserOrderDto {
    private String orderNo;

    private Integer userId;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserOrderDto{" +
                "orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                '}';
    }
}
