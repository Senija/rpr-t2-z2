package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetnaTacka;
    private double krajnjaTacka;
    private boolean pocPripadnost;
    private boolean krajPripadnost;

    public Interval(double pocetna, double krajnja, boolean pocUIntervalu, boolean krajnjaUIntervalu) throws IllegalArgumentException{
        if(pocetna > krajnja) throw new IllegalArgumentException("Pocetna tacka ima vecu vrijednost od krajnje");
        pocetnaTacka = pocetna;
        krajnjaTacka = krajnja;
        pocPripadnost = pocUIntervalu;
        krajPripadnost = krajnjaUIntervalu;
    }

    public Interval(){
        pocetnaTacka = 0;
        krajnjaTacka= 0;
        pocPripadnost = false;
        krajPripadnost = false;
    }

    public boolean isNull(){
        if(krajnjaTacka - pocetnaTacka == 0) return  true;
        return  false;
    }

    public boolean isIn(double nova){
        if(nova > pocetnaTacka && nova < krajnjaTacka) return  true;
        if((nova == pocetnaTacka && pocPripadnost) || (nova == krajnjaTacka && krajPripadnost)) return  true;
        return  false;
    }

    public double getPocetnaTacka() {
        return pocetnaTacka;
    }

    public double getKrajnjaTacka() {
        return krajnjaTacka;
    }
    private static Interval presjek(Interval prvi, Interval drugi){
        if(drugi.pocetnaTacka > prvi.krajnjaTacka || prvi.pocetnaTacka > drugi.krajnjaTacka) return new Interval();
        double pocNovi = Double.max(prvi.pocetnaTacka, drugi.pocetnaTacka);
        double krajNovi = Double.min(prvi.krajnjaTacka, drugi.krajnjaTacka);
        boolean pocPripadnost = false, krajPripadnost = false;
        if(pocNovi == prvi.pocetnaTacka) pocPripadnost = prvi.pocPripadnost;
        else pocPripadnost = drugi.pocPripadnost;
        if(krajNovi == prvi.krajnjaTacka) krajPripadnost = prvi.krajPripadnost;
        else krajPripadnost = drugi.krajPripadnost;
        return new Interval(pocNovi, krajNovi, pocPripadnost, krajPripadnost);

    }

    public Interval intersect(Interval drugi){
        return presjek(this, drugi);
    }

    public  static Interval intersect(Interval prvi, Interval drugi){ // OVO PADA NAPRAVI VECU KRAJNJU OD POCETNE
        return presjek(prvi, drugi);
    }



    @Override
    public String toString() {
        // return "{" + pocetnaTacka + ", " + krajPripadnost + "}";//??? valjdana ovo se misli
        if(pocetnaTacka - krajnjaTacka == 0) return "()";
        else if(pocPripadnost == true && krajPripadnost == true) return "[" + pocetnaTacka + "," + krajnjaTacka + "]";
        else if(pocPripadnost == false && krajPripadnost == false) return "(" + pocetnaTacka + "," + krajnjaTacka + ")";
        else if(pocPripadnost == true && krajPripadnost == false) return "[" + pocetnaTacka + "," + krajnjaTacka + ")";
        else if(pocPripadnost == false && krajPripadnost == true) return "(" + pocetnaTacka + "," + krajnjaTacka + "]";
        return  "";
    }

    @Override
    public boolean equals(Object obj) {
        Interval novi = (Interval)obj;
        if(pocetnaTacka == novi.pocetnaTacka && krajnjaTacka == novi.krajnjaTacka && pocPripadnost == novi.pocPripadnost && krajPripadnost == novi.krajPripadnost) return true;
        else return  false;
    }

}
