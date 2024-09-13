package org.scoula.service;

import org.scoula.dto.InstallmentSavingsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

@Service
public class InstallmentSavingsService {

    @Value("${jdbc.url}")
    private String dbUrl;

    @Value("${jdbc.username}")
    private String dbUsername;

    @Value("${jdbc.password}")
    private String dbPassword;

    public void saveProducts(List<InstallmentSavingsDTO> products) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            String insertSQL = "INSERT INTO installment_savings_products (fin_co_no, kor_co_nm, fin_prdt_cd, fin_prdt_nm, rsrv_type_nm, save_trm, intr_rate, intr_rate2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            for (InstallmentSavingsDTO product : products) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
                    preparedStatement.setString(1, product.getFinCoNo());
                    preparedStatement.setString(2, product.getKorCoNm());
                    preparedStatement.setString(3, product.getFinPrdtCd());
                    preparedStatement.setString(4, product.getFinPrdtNm());
                    preparedStatement.setString(5, product.getRsrvTypeNm());
                    preparedStatement.setLong(6, product.getSaveTrm());
                    preparedStatement.setDouble(7, product.getIntrRate());
                    preparedStatement.setDouble(8, product.getIntrRate2());

                    preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving data", e);
        }
    }
}
