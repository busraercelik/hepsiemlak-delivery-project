package com.bsr.emlak.commons.transformers;

import com.bsr.emlak.commons.dto.request.AdvertRequestDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.Document;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.entity.property.Property;
import com.bsr.emlak.commons.exception.EmlakBuradaAppException;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import com.bsr.emlak.commons.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bsr.emlak.commons.constant.ErrorCode.*;

@Component
public class AdvertTransformer {

    private final PropertyRepository propertyRepository;
    private final EmlakUserRepository emlakUserRepository;

    @Autowired
    public AdvertTransformer(PropertyRepository propertyRepository, EmlakUserRepository emlakUserRepository) {
        this.propertyRepository = propertyRepository;
        this.emlakUserRepository = emlakUserRepository;
    }

    public Advert transform(AdvertRequestDTO request) {
        Optional<Property> property = propertyRepository.findById(request.getPropertyId());
        Optional<EmlakUser> emlakUser = emlakUserRepository.findById(request.getEmlakUserId());

        property.orElseThrow(()->EmlakBuradaAppException.builder()
                .errorCode(PROPERTY_NOT_FOUND.formatted(request.getPropertyId()))
                .httpStatusCode(400)
                .build());
        emlakUser.orElseThrow(()-> EmlakBuradaAppException.builder()
                .errorCode(USER_NOT_FOUND.formatted(request.getEmlakUserId()))
                .httpStatusCode(400)
                .build());

        Advert advert = new Advert();
        advert.setTitle(request.getTitle());
        advert.setDescription(request.getDescription());
        advert.setProperty(property.orElse(null));
        advert.setPostedByEmlakUser(emlakUser.orElse(null));

        List<Document> images = request.getImages()
                .stream()
                .map(url -> {
                    Document imageDoc = new Document();
                    imageDoc.setUrl(url);
                    imageDoc.setDocumentType(Document.DocumentType.IMAGE);
                    emlakUser.ifPresent(nonEmptyEmlakUser -> {
                        imageDoc.setCreatedBy(nonEmptyEmlakUser.getCreatedBy());
                        imageDoc.setModifiedBy(nonEmptyEmlakUser.getCreatedBy());
                        imageDoc.setCreatedBy(nonEmptyEmlakUser.getId());
                        imageDoc.setModifiedBy(nonEmptyEmlakUser.getId());
                    });
                    return imageDoc;
                }).collect(Collectors.toList());

        advert.setImages(images);
        advert.setCost(request.getCost());
        advert.setDuration(request.getDuration());
        advert.setShouldHighlighted(request.getShouldHighlighted());
        advert.setIsReviewed(request.getIsReviewed());
        advert.setPhoneNumber(request.getPhoneNumber());

        emlakUser.ifPresent(nonEmptyEmlakUser->{
            advert.setCreatedBy(nonEmptyEmlakUser.getId());
            advert.setModifiedBy(nonEmptyEmlakUser.getId());
        });

        return advert;
    }
}
