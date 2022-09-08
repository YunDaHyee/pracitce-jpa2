package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery {
	@Id @GeneratedValue
	@Column(name = "DELIVERY_ID")
	private Long id;
	
	// 배송지 주소
	private String city;
	private String street;
	private String zipcode;
	
	private DeliveryStatus status;
	
	@OneToOne(mappedBy = "delivery") // Order랑 양방향할거라고 매핑해주는 것. 어떤 주문에 의해 배송이 되는지를 알고싶어서 넣는 것.
	private Order order;
}
