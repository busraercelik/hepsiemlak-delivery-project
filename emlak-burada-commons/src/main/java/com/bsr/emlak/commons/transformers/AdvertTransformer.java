package com.bsr.emlak.commons.transformers;

import com.bsr.emlak.commons.dto.request.AdvertRequestDTO;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.Document;
import com.bsr.emlak.commons.entity.EmlakUser;
import com.bsr.emlak.commons.entity.property.Property;
import com.bsr.emlak.commons.repository.EmlakUserRepository;
import com.bsr.emlak.commons.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Optional<EmlakUser> emlakUser = emlakUserRepository.findById(request.getUserId());

        property.orElseThrow(()->new RuntimeException(String.format("No property with id %s found!", request.getPropertyId())));
        emlakUser.orElseThrow(()->new RuntimeException(String.format("No user with id %s found!", request.getUserId())));

        Advert advert = new Advert();
        advert.setTitle(request.getTitle());
        advert.setDescription(request.getDescription());
        advert.setProperty(property.orElse(null));
        advert.setPostedBy(emlakUser.orElse(null));

        List<Document> images = request.getImages().stream()
                .map(url -> {
                    Document imageDoc = new Document();
                    imageDoc.setUrl(url);
                    imageDoc.setDocumentType(Document.DocumentType.IMAGE);
                    emlakUser.ifPresent(nonEmptyEmlakUser -> {
                        imageDoc.setCreatedBy(nonEmptyEmlakUser.getCreatedBy());
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
