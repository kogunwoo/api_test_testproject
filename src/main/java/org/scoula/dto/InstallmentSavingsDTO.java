package org.scoula.dto;

public class InstallmentSavingsDTO {
    private String finCoNo;
    private String korCoNm;
    private String finPrdtCd;
    private String finPrdtNm;
    private String rsrvTypeNm;
    private long saveTrm;
    private double intrRate;
    private double intrRate2;

    // VO와 DTO 간 데이터 매핑을 위한 생성자
    public InstallmentSavingsDTO(String finCoNo, String korCoNm, String finPrdtCd, String finPrdtNm, String rsrvTypeNm, long saveTrm, double intrRate, double intrRate2) {
        this.finCoNo = finCoNo;
        this.korCoNm = korCoNm;
        this.finPrdtCd = finPrdtCd;
        this.finPrdtNm = finPrdtNm;
        this.rsrvTypeNm = rsrvTypeNm;
        this.saveTrm = saveTrm;
        this.intrRate = intrRate;
        this.intrRate2 = intrRate2;
    }

    // Getters
    public String getFinCoNo() {
        return finCoNo;
    }

    public String getKorCoNm() {
        return korCoNm;
    }

    public String getFinPrdtCd() {
        return finPrdtCd;
    }

    public String getFinPrdtNm() {
        return finPrdtNm;
    }

    public String getRsrvTypeNm() {
        return rsrvTypeNm;
    }

    public long getSaveTrm() {
        return saveTrm;
    }

    public double getIntrRate() {
        return intrRate;
    }

    public double getIntrRate2() {
        return intrRate2;
    }
}
