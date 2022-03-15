package com.example.emlakburadabanner;

import com.example.emlakburadabanner.dto.request.AddressRequest;
import com.example.emlakburadabanner.dto.request.BannerRequest;
import com.example.emlakburadabanner.dto.response.BannerResponse;
import com.example.emlakburadabanner.model.Address;
import com.example.emlakburadabanner.model.Banner;
import com.example.emlakburadabanner.repository.BannerRepository;
import com.example.emlakburadabanner.service.BannerService;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class BannerServiceTest {

    @InjectMocks
    private BannerService bannerService;

    @Mock
    private BannerRepository bannerRepository;

    @Captor
    ArgumentCaptor<Banner> argumentCaptor;

    @Test
    void getAllBanners() {
        Mockito.when(bannerRepository.findAll())
                .thenReturn(Collections.singletonList(prepareBanner()));
        List<BannerResponse> banners = bannerService.getAllBanners();
        verify(bannerRepository).findAll();
        assertThat(banners.size()).isNotZero();
    }

    @Test
    void saveBanner() {
        Banner banner = prepareBanner();
        bannerService.saveBanner(convertToBannerRequest(banner));
        verify(bannerRepository).save(argumentCaptor.capture());
        Banner value = argumentCaptor.getValue();
        assertEquals(banner.getAdvertNo(), value.getAdvertNo());
        assertEquals(banner.getPhone(), value.getPhone());
        assertEquals(banner.getTotal(), value.getTotal());
        assertEquals(banner.getAddress().getAddressDesc(), value.getAddress().getAddressDesc());
        assertEquals(banner.getAddress().getCity(), value.getAddress().getCity());
        assertEquals(banner.getAddress().getDistrict(), value.getAddress().getDistrict());
    }

    public Banner prepareBanner() {
        Banner banner = new Banner();
        banner.setId(1);
        banner.setPhone("05555555555");
        banner.setTotal(5);
        banner.setAddress(prepareAddress());
        banner.setAdvertNo(123456);
        return banner;
    }

    public BannerRequest convertToBannerRequest(Banner banner) {
        BannerRequest bannerRequest = new BannerRequest();
        bannerRequest.setAdvertNo(banner.getAdvertNo());
        bannerRequest.setAddress(convertToAddressRequest(banner.getAddress()));
        bannerRequest.setPhone(banner.getPhone());
        bannerRequest.setTotal(banner.getTotal());
        return bannerRequest;
    }

    public BannerRequest prepareBannerRequest() {
        BannerRequest banner = new BannerRequest();
        banner.setPhone("05555555555");
        banner.setTotal(5);
        banner.setAddress(convertToAddressRequest(prepareAddress()));
        banner.setAdvertNo(123456);
        return banner;
    }

    public Address prepareAddress() {
        Address address = new Address();
        address.setId(7);
        address.setAddressDesc("home address");
        address.setDistrict("Kadikoy");
        address.setCity("Istanbul");
        return address;
    }

    public AddressRequest convertToAddressRequest(Address address) {
        AddressRequest addressRequest =  new AddressRequest();
        addressRequest.setAddressDesc(address.getAddressDesc());
        addressRequest.setCity(address.getCity());
        addressRequest.setDistrict(address.getDistrict());
        return addressRequest;
    }

}
