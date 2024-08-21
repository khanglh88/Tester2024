package com.basic.bai1;

import java.util.Scanner;

public class MyMainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số thứ nhất x = ");
        double x = Double.parseDouble(scanner.nextLine());

        System.out.print("Nhập số thứ hai y = ");
        double y = Double.parseDouble(scanner.nextLine());

        System.out.print("Nhập lệnh ACTION = ");
        String action = scanner.nextLine();

        double result = 0.0;
        switch (action) {
            case "CONG":
                result = CalculateUtils.tinhTong(x, y);
                break;
            case "TRU":
                result = CalculateUtils.tinhHieu(x, y);
                break;
            case "NHAN":
                result = CalculateUtils.tinhNhan(x, y);
                break;
            case "CHIA":
                if (y == 0) {
                    System.out.println("Lỗi: Không thể chia cho 0");
                    System.exit(0);
                }
                result = x / y;
                break;
            default:
                System.out.println("Giá trị ACTION không hợp lệ");
                System.exit(0);
        }

        System.out.println("Kết quả: " + result);
        scanner.close();
    }
}