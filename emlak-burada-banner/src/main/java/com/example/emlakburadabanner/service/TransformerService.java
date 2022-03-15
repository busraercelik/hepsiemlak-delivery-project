package com.example.emlakburadabanner.service;

import com.example.emlakburadabanner.dto.request.AddressRequest;
import com.example.emlakburadabanner.dto.request.BannerRequest;
import com.example.emlakburadabanner.dto.response.BannerResponse;
import com.example.emlakburadabanner.model.Address;
import com.example.emlakburadabanner.model.Banner;

public class TransformerService {

    public static BannerResponse convertToBannerResponse(Banner banner) {
        BannerResponse response = new BannerResponse();
        response.setId(banner.getId());
        response.setAdvertNo(banner.getAdvertNo());
        response.setPhone(banner.getPhone());
        response.setTotal(banner.getTotal());
        return response;
    }

    public static Banner convertToBanner(BannerRequest request) {
        Banner banner = new Banner();
        banner.setAdvertNo(request.getAdvertNo());
        banner.setPhone(request.getPhone());
        banner.setTotal(request.getTotal());
        banner.setAddress(convertToAddress(request.getAddress()));
        return banner;
    }

    public static AddressRequest convertToAddressRequest(Address address) {
        AddressRequest addressRequest =  new AddressRequest();
        addressRequest.setAddressDesc(address.getAddressDesc());
        addressRequest.setCity(address.getCity());
        addressRequest.setDistrict(address.getDistrict());
        return addressRequest;
    }

    public static Address convertToAddress(AddressRequest addressRequest) {
        Address address = new Address();
        address.setAddressDesc(addressRequest.getAddressDesc());
        address.setCity(addressRequest.getCity());
        address.setDistrict(addressRequest.getDistrict());
        return address;
    }

}
