package com.top.effitopia.mapper;

import com.top.effitopia.domain.Address;
import com.top.effitopia.domain.CustomerAnswer;
import com.top.effitopia.domain.CustomerInquiry;
import com.top.effitopia.domain.Member;
import com.top.effitopia.enumeration.MemberRole;
import com.top.effitopia.enumeration.MemberStatus;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class CSCMapperTests {

    @Autowired
    private CSCMapper cscMapper;
    @Autowired
    private MemberMapper memberMapper;




    @Test
    public void insertInquiryTest() {
       /* Member admin = Member.builder()
                .username("admin" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("총관리자")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.ADMIN)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();
        memberMapper.insert(admin);
        Member warehouseManager = Member.builder()
                .username("manager" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("창고관리자")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.WAREHOUSE_MANAGER)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();
        memberMapper.insert(warehouseManager);
        Member proprietor = Member.builder()
                .username("proprietor" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("판매자1")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.BUSINESS_PROPRIETOR)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .businessNumber("1234567890")
                .build();

        memberMapper.insert(proprietor);
        Member dispatcher = Member.builder()
                .username("dispatcher" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("판매자2")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.BUSINESS_PROPRIETOR)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();
            memberMapper.insert(dispatcher);

        memberMapper.insert(proprietor);
        Member seller = Member.builder()
                .username("seller" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("판매자3")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.BUSINESS_PROPRIETOR)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();
        memberMapper.insert(seller);*/


        Optional<Member> optionalMember = memberMapper.selectOne(6);

        if(optionalMember.isPresent()) {
            Member member = optionalMember.get();
            CustomerInquiry customerInquiry = CustomerInquiry.builder()
                    .member(member)
                    .inquiryTitle("테스트1")
                    .inquiryContent("내용테스트")
                    .inquiryWriter(member.getName())
                    .inquiryState(false).build();

            cscMapper.insertInquiry(customerInquiry);
            log.info(member);
        }



    }

    @Test
    public void selectOneInquiryTest() {
        Optional<CustomerInquiry>  customerInquiry = cscMapper.selectOneInquiry(1);

        customerInquiry.ifPresent(inquiry -> log.info("customerInquiry : " + inquiry));
        log.info("num : " + customerInquiry.get().getMember().getName());
        Optional<Member> member = memberMapper.selectOne(6);
        member.ifPresent(mem -> log.info("member : " + mem.getName()));


    }

    @Test
    public void updateInquiryTest() {
        CustomerInquiry customerInquiry = CustomerInquiry.builder().id(5).inquiryContent("내용 수정테스트").build();
        cscMapper.updateInquiry(customerInquiry);
        log.info(customerInquiry.toString());
    }

    @Test
    public void removeInquiryTest() {
        cscMapper.deleteInquiry(2);
    }

    @Test
    public void listInquiryTest() {
        List<CustomerInquiry> customerInquiryList = cscMapper.selectInquiryList();

        customerInquiryList.forEach(inquiry -> log.info(inquiry.toString()));

    }

    @Test
    public void insertAnswerTest() {
        Member member = Member.builder()
                .id(1)
                .username("admin" + (int)(Math.random() * 10000))
                .password(UUID.randomUUID().toString().substring(0, 33))
                .name("관리자")
                .phone("01012345678")
                .email("example@gmail.com")
                .status(MemberStatus.REGISTER_REQUEST)
                .role(MemberRole.ADMIN)
                .address(Address.builder().zipCode("").roadNameAddress("").lotNumberAddress("").detailAddress("").build())
                .build();
        memberMapper.insert(member);

        Optional<CustomerInquiry> customerInquiry = cscMapper.selectOneInquiry(3);
        log.info(customerInquiry.toString());
        if(customerInquiry.isPresent()) {
            CustomerAnswer customerAnswer = CustomerAnswer.builder()
                    .AnswerContent("답변테스트2")
                    .AnswerWriter(member.getName())
                    .inquiry(customerInquiry.get()).build();
            cscMapper.insertAnswer(customerAnswer);
            log.info(customerAnswer.toString());
        }

    }

    @Test
    public void selectOneAnswerTest() {
        Optional<CustomerAnswer> customerAnswer = cscMapper.selectOneAnswer(2);
        customerAnswer.ifPresent(answer -> log.info(answer.toString()));

    }

    @Test
    public void updateAnswer() {
        CustomerAnswer customerAnswer = CustomerAnswer.builder().id(2).AnswerContent("내용 수정 테스트").build();
        log.info(customerAnswer.toString());
        cscMapper.updateAnswer(customerAnswer);
    }

    @Test
    public void removeAnswerTest() {
        cscMapper.deleteAnswer(2);
    }
}
