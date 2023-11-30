package location;

public abstract class Business {

    public abstract String getContactInfo();

    public abstract String getAddress();

    public enum BusinessType {
        soleProprietorship("SoleProprietorship"),
        partnership("Partnership"),
        corporation("Corporation"),
        sCorporation("SCorporation");

        private final String businessType;

        BusinessType(String businessType) {
            this.businessType = businessType;
        }

        public String getBusinessType() {
            return businessType;
        }
    }

}
