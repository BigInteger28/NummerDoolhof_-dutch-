package main;

public class RunConsole {

	public static void main(String[] args) {
		int[] bord = new int[] {1, 2, 3, 4, 5, 6, 
								4, 2, 1, 2, 4, 3,
								2, 2, 1, 1, 1, 1,
								2, 2, 4, 3, 1, 1,
								1, 2, 3, 4, 5, 6};
		int breedte = 6;
		
		Spel spel = new Spel(bord, breedte);
		spel.losHetSpelOp();
	}

}
