/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package com.hyperbeast.entity;

import com.hyperbeast.form.quanLyKM;
import java.util.Date;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 *
 * @author Admin
 */
public class Test_Mau {
    
    //KHAI BÁO MẪU DỮ LIỆU TEST
   quanLyKM quanKM;
   int[] listChoice;
   int[] listMaKM;
   String[] listTenKM;
   boolean[] listKetQua;
    Date[] listNgayBatDau;
   //TIẾP TỤC KHAI BÁO CÁC TRƯỜNG CÒN LẠI

    @BeforeTest
    public  void setUP() throws Exception {
        
        quanKM = new quanLyKM();
        listChoice = new int[]{1,2,1,3,2,1};
        listMaKM = new int[] {1,2,3,4,5,6,7};
        listTenKM = new String[]{"km01", "km02","km03"};
        listKetQua = new boolean[]{true, false,true,true};
        //TIẾP TỤC KHAI BÁO CÁC TRƯỜNG CÒN LẠI
    }
    @Test
    public void test() {
        int countPass = 0; // Biến đếm số test pass
        for(int i = 0; i < listMaKM.length; i++) {
            try {
                System.out.println("chạy test: " + (i + 1));
                //boolean actual = quanKM.validateKhuyenMaiTest(listChoice[i], listMaKM[i], listTenKM[i], ngayBD, ngayKT, ARRAY_MISMATCH_TEMPLATE, 0, 0, 0);
                //assertEquals(actual, listKetQua[i]);
                countPass++; // Tăng biến đếm nếu test pass
                System.out.println("số test pass: " + countPass);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("số test pass: " + countPass);
            }
            
        }
        
    }

    @AfterTest
    public  void close() throws Exception {
        //ĐÓNG DỮ LIỆU TEST
        quanKM = null;
        listChoice = null;
        listKetQua = null;
        listMaKM = null;
        listTenKM = null;
    }
    
}
