package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Message;
import ssafy.project.jobterview.dto.MessageDto;
import ssafy.project.jobterview.repository.MemberRepository;
import ssafy.project.jobterview.repository.MessageRepository;


@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;

    @Override
    public void save(MessageDto mDto) {
        Member Sender = memberRepository.findByNickname(mDto.getSenderNickname()).orElseThrow(() -> new IllegalArgumentException());
        Member Receiver = memberRepository.findByNickname(mDto.getReceiverNickname()).orElseThrow(()->new IllegalArgumentException());
        Message message = new Message(0L,Sender,Receiver, mDto.getContent(),true,true);
        messageRepository.save(message);
    }

    @Override
    public Page<Message> findAllByFromMemberVisibleAndReceiver(Pageable pageable, String nickname){
        return messageRepository.findAllByFromMemberVisibleAndReceiver(pageable ,nickname);
    }
    @Override
    public Page<Message> findAllByToMemberVisibleAndSender (Pageable pageable, String nickname){
        return messageRepository.findAllByToMemberVisibleAndSender(pageable,nickname);
    }

    @Override
    public Message findById(Long id){
        Message m = messageRepository.findById(id).orElseThrow(()->new IllegalArgumentException());
        return m;
    }
    @Override
    public Message read(Long id){
        Message m = messageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        m.setRead(true); // 읽음 처리
        messageRepository.save(m);
        return m;
    }
    @Override
    public void receiveMessageDelete (Long id){
        Message m = messageRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
        m.setFromMemberVisible(false);
        messageRepository.save(m);
    }
    @Override
    public void sendMessageDelete (Long id){
        Message m = messageRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
        m.setToMemberVisible(false);
        messageRepository.save(m);
    }


}
