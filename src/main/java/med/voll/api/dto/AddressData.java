package med.voll.api.dto;

public record AddressData(
        String street,
        String district,
        String city,
        String number,
        String modifier) {}
