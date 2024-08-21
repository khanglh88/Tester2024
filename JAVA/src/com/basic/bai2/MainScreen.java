package com.basic.bai2;

import java.util.Scanner;

public class MainScreen {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Nhập vào số nguyên N = ");
		int N = scanner.nextInt();		

			if(N>0) {
				int sumChan = 0;
				int sumLe = 0;
				int countChiaHet = 0;
				
				for(int i = 0; i<=N; i++) {	
				 if(i % 2 == 0){
					 sumChan += i;
				 } else {
					 sumLe += i;
				 } if (i % 3 == 0 && i % 2 != 0) {
					 countChiaHet++;
				 }				 
			}
				System.out.println("Tổng các số chẵn từ 0 đến " + N + " là: " + sumChan);
				System.out.println("Tổng các số lẽ từ 0 đến " + N + " là: " + sumLe);
				System.out.println("Có " + countChiaHet + " số từ 0 đến " + N + " Chia hết cho 3 nhưng không chia hết cho 2");
		} else {
			System.out.println("Phải nhập số nguyên N > 0");
		}
			scanner.close();
	}
}
