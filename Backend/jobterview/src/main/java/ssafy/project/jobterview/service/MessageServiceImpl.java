package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Message;
import ssafy.project.jobterview.repository.MessageRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    @Override
    public Message save(Message m) {
        return messageRepository.save(m);
    }
    @Override
    public Optional<Message> findAllByToMemberVisibleAndReceiver(Message m){
        return messageRepository.findAllByToMemberVisibleAndReceiver(m);
    }
    @Override
    public Optional<Message> findAllByFromMemberVisibleAndSender (Message m){
        return messageRepository.findAllByFromMemberVisibleAndSender(m);
    }

    @Override
    public Message findById(Long id){
        return messageRepository.findById(Long id);
    }
    @Override
    public Message read(Long id){
        return messageRepository.update(id);
    }
    @Override
    public Message receiveMessageDelete (Long id){
        messageRepository.update(id);
    }
    @Override
    public Message sendMessageDelete (Long id){
        return messageRepository.update(id);
    }


}
