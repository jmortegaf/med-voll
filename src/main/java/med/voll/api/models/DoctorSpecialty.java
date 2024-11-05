package med.voll.api.models;

public enum DoctorSpecialty {

    CARDIOLOGY("Cardiology"),
    DERMATOLOGY("Dermatology");

    private String specialty_en;

    DoctorSpecialty(String specialty_en){
        this.specialty_en=specialty_en;
    }
    public static DoctorSpecialty fromString(String specialty){
        for(DoctorSpecialty s : DoctorSpecialty.values()){
            if(s.specialty_en.equalsIgnoreCase(specialty))return s;
        }
        throw new IllegalArgumentException("Specialty not found: "+specialty);
    }

}
