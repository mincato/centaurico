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

}
