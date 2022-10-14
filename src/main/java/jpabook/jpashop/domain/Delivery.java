package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @JsonIgnore // Json으로 보낼경우.. 양방향 연관관계에선 쿼리를 서로 주고 받기 때문에 한쪽(반대편)은 JsonIgnore를 설정해주자.
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // ORDINAL은 정수가 들어감. -> 무조건 STRING으로 써야함
    private DeliveryStatus status; //READY, COMP
}
