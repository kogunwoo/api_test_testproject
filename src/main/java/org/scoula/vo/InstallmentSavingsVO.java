package org.scoula.vo;

public class InstallmentSavingsVO {
    private String finCoNo;
    private String korCoNm;
    private String finPrdtCd;
    private String finPrdtNm;
    private String rsrvTypeNm;
    private long saveTrm;
    private double intrRate;
    private double intrRate2;

    // Getters와 Setters
    public String getFinCoNo() {
        return finCoNo;
    }

    public void setFinCoNo(String finCoNo) {
        this.finCoNo = finCoNo;
    }

    public String getKorCoNm() {
        return korCoNm;
    }

    public void setKorCoNm(String korCoNm) {
        this.korCoNm = korCoNm;
    }

    public String getFinPrdtCd() {
        return finPrdtCd;
    }

    public void setFinPrdtCd(String finPrdtCd) {
        this.finPrdtCd = finPrdtCd;
    }

    public String getFinPrdtNm() {
        return finPrdtNm;
    }

    public void setFinPrdtNm(String finPrdtNm) {
        this.finPrdtNm = finPrdtNm;
    }

    public String getRsrvTypeNm() {
        return rsrvTypeNm;
    }

    public void setRsrvTypeNm(String rsrvTypeNm) {
        this.rsrvTypeNm = rsrvTypeNm;
    }

    public long getSaveTrm() {
        return saveTrm;
    }

    public void setSaveTrm(long saveTrm) {
        this.saveTrm = saveTrm;
    }

    public double getIntrRate() {
        return intrRate;
    }

    public void setIntrRate(double intrRate) {
        this.intrRate = intrRate;
    }

    public double getIntrRate2() {
        return intrRate2;
    }

    public void setIntrRate2(double intrRate2) {
        this.intrRate2 = intrRate2;
    }
}
