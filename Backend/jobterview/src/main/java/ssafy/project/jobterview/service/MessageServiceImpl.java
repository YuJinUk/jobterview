package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Message;
import ssafy.project.jobterview.repository.MessageQueryRepository;
import ssafy.project.jobterview.repository.MessageRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageQueryRepository messageQueryRepository;
    @Override
    public Message save(Message m) {
        return messageRepository.save(m);
    }
    @Override
    public Page<Message> findAllByFromMemberVisibleAndReceiver(Pageable pageable, Message m){
        return messageQueryRepository.findAllByFromMemberVisibleAndReceiver(pageable ,m);
    }
    @Override
    public Page<Message> findAllByToMemberVisibleAndSender (Pageable pageable, Message m){
        return messageQueryRepository.findAllByToMemberVisibleAndSender(pageable, m);
    }

    @Override
    public Message findById(Long id){
        Message m = messageRepository.findById(id).orElseThrow(()->new IllegalArgumentException());
        return m;
    }
    @Override
    public void read(Long id){
        Message m = messageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        m.setRead(false); // 읽음 처리
    }
    @Override
    public void receiveMessageDelete (Long id){
        Message m = messageRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
        m.setFromMemberVisible(false);
    }
    @Override
    public void sendMessageDelete (Long id){
        Message m = messageRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
        m.setToMemberVisible(false);
    }


}
