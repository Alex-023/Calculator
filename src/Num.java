

public enum Num {
    I(1, StatNum.NONE), II(2, StatNum.NONE), III(3, StatNum.NONE), IV(4, StatNum.NONE), V(5, StatNum.NONE), VI(6, StatNum.NONE), VII(7, StatNum.NONE), VIII(8, StatNum.NONE), IX(9, StatNum.NONE), X(10, StatNum.NONE), NIHIL(0, StatNum.NONE);

    private int arab;
    private StatNum status;
    //private String rim;
    Num(int arab, StatNum status ) {
        this.arab=arab;
        this.status=status;
    }

    //Num(String rim){
    //    this.rim=name();
    //}
    public int getArab() {
        return arab;
    }
    public String getRim() {return this.name();}
    public void setStatus(StatNum status){
        this.status = status;
    }
    public StatNum getStatus() {return this.status;}

}
