package co.centauri.developer;

import co.centauri.random.RandomInteger;

public class DeveloperBuilder {

    public static Developer createGerman() {
        Developer developer = new Developer();
        developer.setId(RandomInteger.getNext());
        developer.setName("German");
        developer.setLastName("Hohmann");
        developer.setSeniority(Seniority.SENIOR);
        return developer;
    }

    public static Developer createPablo() {
        Developer developer = new Developer();
        developer.setId(RandomInteger.getNext());
        developer.setName("Pablo");
        developer.setLastName("Blauer");
        developer.setSeniority(Seniority.SENIOR);
        return developer;
    }

    public static Developer createAdrian() {
        Developer developer = new Developer();
        developer.setId(RandomInteger.getNext());
        developer.setName("Adrian");
        developer.setLastName("Paredes");
        developer.setSeniority(Seniority.SENIOR);
        return developer;
    }

    public static Developer createKyloRen() {
        Developer developer = new Developer();
        developer.setId(RandomInteger.getNext());
        developer.setName("Kylo");
        developer.setLastName("Ren");
        developer.setSeniority(Seniority.JUNIOR);
        return developer;
    }

    public static Developer createDarthVader() {
        Developer developer = new Developer();
        developer.setId(RandomInteger.getNext());
        developer.setName("Darth");
        developer.setLastName("Vader");
        developer.setSeniority(Seniority.SENIOR);
        return developer;
    }

    public static Developer createDarthMaul() {
        Developer developer = new Developer();
        developer.setId(RandomInteger.getNext());
        developer.setName("Darth");
        developer.setLastName("Maul");
        developer.setSeniority(Seniority.SEMI_SENIOR);
        return developer;
    }

}
