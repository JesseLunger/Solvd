package enums;

public enum BusinessType {
    SOLE_PROPRIETORSHIP("SoleProprietorship"),
    PARTNERSHIP("Partnership"),
    CORPORATION("Corporation"),
    S_CORPORATION("SCorporation");

    private final String businessType;

    BusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessType() {
        return businessType;
    }
}
