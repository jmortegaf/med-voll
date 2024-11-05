package med.voll.api.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.AddressData;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String district;
    private String city;
    private String number;
    private String modifier;

    public Address(AddressData data) {
        this.street=data.street();
        this.district=data.district();
        this.city=data.city();
        this.number= data.number();
        this.modifier= data.modifier();
    }

    public void update(@Valid AddressData address) {
        if(address==null)return;
        street=address.street()!=null?address.street():street;
        district=address.district()!=null?address.district():district;
        city=address.city()!=null?address.city():city;
        number=address.number()!=null?address.number():number;
        modifier=address.modifier()!=null?address.modifier():modifier;
    }
}
