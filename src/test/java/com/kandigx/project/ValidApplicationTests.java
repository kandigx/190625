package com.kandigx.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] pa) {
		int[] arr = new int[]{12, 4, 25, 14, 1, 33, 31, 53, 13, 9, 42, 15};

		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;

			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}

			int temp = arr[i];


		}



		for(int i= 0; i < arr.length -1; i++){
			int minIndex = i;
			//选择
			for(int j = i + 1; j < arr.length; j++){
				if(arr[j] < arr[minIndex]){
					minIndex = j;
				}
			}
			//交换
			int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

}
