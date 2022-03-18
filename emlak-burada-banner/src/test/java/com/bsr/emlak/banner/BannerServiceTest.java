package com.bsr.emlak.banner;

import com.bsr.emlak.banner.service.BannerService;
import com.bsr.emlak.commons.dto.request.BannerRequestDTO;
import com.bsr.emlak.commons.dto.response.BannerResponseDTO;
import com.bsr.emlak.commons.entity.Address;
import com.bsr.emlak.commons.entity.Advert;
import com.bsr.emlak.commons.entity.Banner;
import com.bsr.emlak.commons.entity.property.Residential;
import com.bsr.emlak.commons.enums.AdvertStatus;
import com.bsr.emlak.commons.enums.PropertyType;
import com.bsr.emlak.commons.repository.AdvertRepository;
import com.bsr.emlak.commons.repository.BannerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.bsr.emlak.banner.BannerTestConstants.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BannerServiceTest {

    @InjectMocks
    private BannerService bannerService;
    @Mock
    private BannerRepository bannerRepository;
    @Mock
    private AdvertRepository advertRepository;
    @Captor
    ArgumentCaptor<Banner> argumentCaptor;

    @Test
    void getAllBanners() {
        Mockito.when(bannerRepository.findAll())
                .thenReturn(Collections.singletonList(prepareBanner()));
        List<BannerResponseDTO> banners = bannerService.getAllBanners();
        verify(bannerRepository).findAll();
        assertThat(banners.size()).isNotZero();
    }

    @Test
    void saveBanner() {

        Banner banner = prepareBanner();
        when(advertRepository.findByAdvertUUID(any())).thenReturn(prepareAdvert());
        bannerService.saveBanner(convertToBannerRequest(banner));
        verify(bannerRepository).save(argumentCaptor.capture());
        Banner value = argumentCaptor.getValue();
        assertEquals(banner.getAdvertUUID(), value.getAdvertUUID());
        assertEquals(banner.getPhoneNumber(), value.getPhoneNumber());
        assertEquals(banner.getCost(), value.getCost());
        assertEquals(banner.getCity(), value.getCity());
        assertEquals(banner.getDistrict(), value.getDistrict());
        assertEquals(banner.getTitle(), value.getTitle());
        assertEquals(banner.getPropertyType(), value.getPropertyType());
    }

    public Banner prepareBanner() {
        Banner banner = new Banner();
        banner.setAdvertUUID(TEST_AD_ID);
        banner.setCity(TEST_CITY);
        banner.setId(TEST_ID);
        banner.setImageList(Collections.singletonList(TEST_IMAGE_URL));
        banner.setGrossSquareMeter(TEST_GROSS_SQUARE_METER);
        banner.setPhoneNumber(TEST_PHONE_NUMBER);
        banner.setCreatedAt(LocalDateTime.now());
        banner.setTitle(TEST_TITLE);
        banner.setPropertyType(PropertyType.RESIDENTIAL);
        banner.setDistrict(TEST_DISTRICT);
        banner.setCost(COST);
        return banner;
    }

    public Advert prepareAdvert(){
        Advert advert = new Advert();
        advert.setAdvertUUID(TEST_AD_ID);
        advert.setTitle(TEST_TITLE);
        advert.setDescription(TEST_DESCRIPTION);
        advert.setImageList(Collections.singletonList(TEST_IMAGE_URL));
        advert.setCost(COST);
        advert.setDuration(TEST_DURATION);
        advert.setPhoneNumber(TEST_PHONE_NUMBER);
        advert.setAdvertStatus(AdvertStatus.ACTIVE);

        advert.setProperty(prepareResidential());

        return advert;
    }

    public Residential prepareResidential(){
        Residential residential = new Residential();

        residential.setId(TEST_ID);
        residential.setFloor(2);
        residential.setCreatedAt(LocalDateTime.now());
        residential.setDescription(TEST_DESCRIPTION);
        residential.setGrossSquareMeter(TEST_GROSS_SQUARE_METER);
        residential.setBuildingAge(8);
        residential.setIsEligibleForCredit(Boolean.TRUE);
        residential.setNoOfBath(2);
        residential.setNoOfRoom(3);
        residential.setIsForSale(Boolean.TRUE);

        residential.setAddress(prepareAddress());

        return residential;
    }

    public Address prepareAddress() {
        Address address = new Address();
        address.setId(TEST_ID);
        address.setAddressDesc(TEST_ADDRESS_DESCRIPTION);
        address.setDistrict(TEST_DISTRICT);
        address.setCity(TEST_CITY);
        return address;
    }


    public BannerRequestDTO convertToBannerRequest(Banner banner) {
        BannerRequestDTO bannerRequestDTO = new BannerRequestDTO();
        bannerRequestDTO.setAdvertUUID(banner.getAdvertUUID());
        return bannerRequestDTO;
    }

    public BannerRequestDTO prepareBannerRequest() {
        BannerRequestDTO banner = new BannerRequestDTO();
        banner.setAdvertUUID(TEST_AD_ID);
        return banner;
    }

}
