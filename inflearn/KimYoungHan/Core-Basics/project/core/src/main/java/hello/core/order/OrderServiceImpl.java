package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository
                                    = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy
                                    = new FixDiscountPolicy();

    // 단일 책임 원칙을 잘 지켜진 것
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // Order는 할인과는 아무 관련이 없다!
        int discoutPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discoutPrice);
    }
}
