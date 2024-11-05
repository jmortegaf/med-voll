package med.voll.api.dto;

public record DoctorRegisterData(
        String name,
        String email,
        String documentId,
        String specialty,
        AddressData address) {}
