package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS") // DB마다 다른데 ORDER BY에서 ORDER가 예약어로 달려있는 경우가 있다. 그래서 테이블명으로 되어있는 데가 있고 안되는 데가 있어가지고 그냥 ORDERS로 함 
public class Order {
	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
	
	// 이전에는 멤버아이디 외래키값을 매핑해서 엔티티에서 그대로 가지고 있었는데 이젠 필요가 없어짐.
	/*
	@Column(name = "MEMBER_ID")
	private Long memberId; // 주문인데 누가 주문했는지 알기 위함
	*/
	
	// 나를 주문한 멤버 - Orders 입장에서의 member.(나를 주문한 회원은 하나). 멤버입장에서는 하나의 회원이 여러번 주문할 수 있으니까 일다대인데

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	// 양방향이라서 연관관계 편의 메서드 만들어줌
	public void addOrderItem(OrderItem orderItem) {
		/*
			현재 나의 Order를 넣어서 양방향 연관관계를 만들어줌.
			주문 객체를 만들어서 원하는 OrderItem들을 쭉쭉 넣을 수 있다.
		 */
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
}
