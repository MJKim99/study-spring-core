package hello.core;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
