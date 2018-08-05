package ua.nure.thao.Practice4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class Part2Test {

	@Test
	public void test() throws IOException {
		Part2 part2 = new Part2();
		int[] list = { 36, 42, 40, 1, 21, 4, 21, 29, 28, 17};
	
		part2.write2File("part2.txt", list);
		int[] list2 = part2.readIntFile("part2.txt");
		part2.Sort(list2);
		StringBuilder actual = new StringBuilder();
		for(int i : list2) {
			actual.append(String.valueOf(i)).append(" ");
		}
		String expected =  "1 4 17 21 21 28 29 36 40 42 ";
		assertEquals(expected, actual.toString());
	}

}
