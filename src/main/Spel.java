package main;

public class Spel {
	private int[] bord;
	private int breedte;
	private int aantalStappen;
	private int totaalStappen;
	private int maxStappen;
	public boolean metStappen;
	
	public Spel(int[] ingegevenBord, int breedte) {
		this.breedte = breedte;
		bord = ingegevenBord;
	}
	
	private boolean isInLaatsteRij(int coord) {
		if (coord <= bord.length && coord >= bord.length-breedte) {
			return true;
		} else {
			return false;
		}
		//return (bord.length - breedte) <= coord && coord <= bord.length;
		//return coord <= bord.length && coord >= bord.length-breedte;
	}
	
	private boolean isOpLinkseRand(int coord) {
		return coord % 6 == 0;
	}
	
	private boolean isOpRechtseRand(int coord) {
		return (coord + 1) % breedte == 0;
	}
	
	//Vereenvoudig in stappen met hulpmethodes
	//
	//
	private int gaNaarLinksBoven(int coord) {
		int huidigePositie = coord;
		if (isOpLinkseRand(huidigePositie)) huidigePositie += (2 * breedte) - 1;
		else huidigePositie += breedte - 1;
		if (huidigePositie >= bord.length) huidigePositie -= bord.length;
		return huidigePositie;
	}
	
	private int gaNaarLinks(int coord) {
		int huidigePositie = coord;
		if (isOpLinkseRand(huidigePositie)) huidigePositie += breedte - 1;
		else huidigePositie -= 1;
		return huidigePositie;
	}
	
	private int gaNaarRechtsBoven(int coord) {
		int huidigePositie = coord;
		if (isOpRechtseRand(huidigePositie)) huidigePositie += 1;
		else huidigePositie += breedte + 1;
		if (huidigePositie >= bord.length) huidigePositie -= bord.length;
		return huidigePositie;
	}
	
	private int gaNaarRechts(int coord) {
		int huidigePositie = coord;
		if (isOpRechtseRand(huidigePositie)) huidigePositie -= (breedte - 1);
		else huidigePositie += 1;
		return huidigePositie;
	}	
	//
	//
	//Tot hier
	
	private int getX(int coord) {
		return (coord % breedte) + 1;
	}
	
	private int getY(int coord) {
		return (coord / breedte) + 1;
	}
	
	private int volgendePlaats(int coord) {
		int huidigePositie = coord;
		int aantal = bord[coord];
		
		if (bord[huidigePositie] % 2 == 1) { //Als het een oneven volgnummer is (naar linksboven)
			for (int i = aantal; i > 0; i--) {
				huidigePositie = gaNaarLinksBoven(huidigePositie);			
				aantalStappen++;
				totaalStappen++;
				if (metStappen) System.out.println("Stap " + aantalStappen + " naar (" + getX(huidigePositie) + ", " + getY(huidigePositie) + ") met als waarde: " + bord[huidigePositie]);
			}
			huidigePositie = gaNaarRechts(huidigePositie);
			aantalStappen++;
			totaalStappen++;
			if (metStappen) System.out.println("Stap " + aantalStappen + " naar (" + getX(huidigePositie) + ", " + getY(huidigePositie) + ") met als waarde: " + bord[huidigePositie]);
			
		} else { //Als het een even volgnummer is (naar rechtsboven)
			for (int i = aantal; i > 0; i--) {
				huidigePositie = gaNaarRechtsBoven(huidigePositie);				
				aantalStappen++;
				totaalStappen++;
				if (metStappen) System.out.println("Stap " + aantalStappen + " naar (" + getX(huidigePositie) + ", " + getY(huidigePositie) + ") met als waarde: " + bord[huidigePositie]);
			}
			huidigePositie = gaNaarLinks(huidigePositie);
			aantalStappen++;
			totaalStappen++;
			if (metStappen) System.out.println("Stap " + aantalStappen + " naar (" + getX(huidigePositie) + ", " + getY(huidigePositie) + ") met als waarde: " + bord[huidigePositie]);
		}	
		return huidigePositie;
	}
	
	private int eindPlaats(int coord) {		
		int huidigePositie = coord;
		aantalStappen = 0;
		
		while (!isInLaatsteRij(huidigePositie) && aantalStappen <= maxStappen) {
			huidigePositie = volgendePlaats(huidigePositie);
		}
		return huidigePositie;
	}
	
	private int berekenPuzzelRating() {
		return totaalStappen;
	}
	
	
	public void losHetSpelOp() {
		metStappen = true;
		maxStappen = 1000;
		totaalStappen = 0;
		for (int i = 0; i < breedte; i++) {
			int eindPlaats = eindPlaats(i);
			System.out.println("Kolom " + (i + 1) + " met als waarde " + bord[i] + " , eindigt op kolom " + (1 + (eindPlaats % breedte)) + " met als score: " + bord[eindPlaats] + "\nAantal stappen: " + aantalStappen + "\n\n");
		}
		System.out.println("Puzzel rating: " + berekenPuzzelRating());
	}

}
