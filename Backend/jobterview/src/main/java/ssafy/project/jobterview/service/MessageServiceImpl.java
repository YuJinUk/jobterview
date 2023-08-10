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
    private final MemberService memberService;

    @Override
    public void save(MessageDto mDto) {
        Member Sender = memberService.findByNickname(mDto.getSenderNickname());
        Member Receiver = memberService.findByNickname(mDto.getReceiverNickname());
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
        return messageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Message read(Long id){
        Message findMessage = findById(id);
        findMessage.read(); // 읽음 처리
        messageRepository.save(findMessage);
        return findMessage;
    }
    @Override
    public void receiveMessageDelete (Long id){
        Message findMessage = findById(id);
        findMessage.changeFromMemberVisible(false);
        messageRepository.save(findMessage);
    }
    @Override
    public void sendMessageDelete (Long id){
        Message findMessage = findById(id);
        findMessage.changeToMemberVisible(false);
        messageRepository.save(findMessage);
    }

    @Override
    public Page<Message> findAllWithPaging(Pageable pageable) {
        System.out.println("messageService");

        return messageRepository.findAll(pageable);
    }
}