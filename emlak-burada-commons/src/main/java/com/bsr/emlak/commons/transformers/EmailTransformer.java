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
        EmlakUser emlakUser = emlakUserRepository.getById(emailMessageRequestDTO.getEmlakUserId());
        Email email = new Email();
        email.setBody(emailMessageRequestDTO.getBody());
        email.setToEmlakUser(emlakUser);
        email.setCreatedAt(emailMessageRequestDTO.getSentTimeStamp());
        email.setCreatedBy(emailMessageRequestDTO.getEmlakUserId());
        email.setSentTimeStamp(emailMessageRequestDTO.getSentTimeStamp());
        email.setSubject(emailMessageRequestDTO.getSubject());
        return email;
    }

    public static class Request{
        public static EmailMessageRequestDTO transform(Email email){
            EmailMessageRequestDTO emailMessageRequestDTO = EmailMessageRequestDTO
                    .builder()
                    .userName(email.getToEmlakUser().getFullName())
                    .toEmail(email.getToEmlakUser().getEmail())
                    .subject(email.getSubject())
                    .body(email.getBody())
                    .sentTimeStamp(email.getSentTimeStamp())
                    .build();
            emailMessageRequestDTO.setEmlakUserId(email.getToEmlakUser().getId());
            return emailMessageRequestDTO;
        }
    }

}
