package com.marlonpatrick.tacocloud.order;

public interface OrderMessagingSenderGateway {

	void sendOrder(Order order);

}
