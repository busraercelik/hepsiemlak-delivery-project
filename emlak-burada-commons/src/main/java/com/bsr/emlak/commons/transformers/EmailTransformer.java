package com.bsr.emlak.commons.transformers;

import com.bsr.emlak.commons.dto.request.EmailMessageRequestDTO;
import com.bsr.emlak.commons.entity.Email;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import org.springframework.stereotype.Component;

@Component
public class EmailTransformer {

    private final EmlakUserRepository emlakUserRepository;

    public EmailTransformer(EmlakUserRepository emlakUserRepository) {
        this.emlakUserRepository = emlakUserRepository;
    }

    public Email transform(EmailMessageRequestDTO emailMessageRequestDTO){
        EmlakUser emlakUser = emlakUserRepository.getById(emailMessageRequestDTO.getToEmlakUserId());
        Email email = new Email();
        email.setBody(emailMessageRequestDTO.getBody());
        email.setToEmlakUser(emlakUser);
        email.setCreatedAt(emailMessageRequestDTO.getSentTimeStamp());
        email.setCreatedBy(emailMessageRequestDTO.getToEmlakUserId());
        email.setSentTimeStamp(emailMessageRequestDTO.getSentTimeStamp());
        email.setSubject(emailMessageRequestDTO.getSubject());
        return email;
    }

    public static class Request{
        public static EmailMessageRequestDTO transform(Email email){
            return EmailMessageRequestDTO
                    .builder()
                    .toEmlakUserId(email.getId())
                    .userName(email.getToEmlakUser().getFullName())
                    .toEmail(email.getToEmlakUser().getEmail())
                    .subject(email.getSubject())
                    .body(email.getBody())
                    .sentTimeStamp(email.getSentTimeStamp())
                    .build();
        }
    }

}
