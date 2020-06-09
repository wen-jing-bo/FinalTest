package com.xiaowen.Attribute;

public abstract class Attribute {
    private String name;
    private int Hp;
    private int Mp;
    private int Exp;
    private int Hpadd;
    private int Mpadd;
    private int Expadd;
    private int DamagePoint;

    public Attribute() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return Hp;
    }

    public void setHp(int hp) {
        Hp = hp;
    }

    public int getMp() {
        return Mp;
    }

    public void setMp(int mp) {
        Mp = mp;
    }

    public int getExp() {
        return Exp;
    }

    public void setExp(int exp) {
        Exp = exp;
    }

    public int getHpadd() {
        return Hpadd;
    }

    public void setHpadd(int hpadd) {
        Hpadd = hpadd;
    }

    public int getMpadd() {
        return Mpadd;
    }

    public void setMpadd(int mpadd) {
        Mpadd = mpadd;
    }

    public int getExpadd() {
        return Expadd;
    }

    public void setExpadd(int expadd) {
        Expadd = expadd;
    }

    public int getDamagePoint() {
        return DamagePoint;
    }

    public void setDamagePoint(int damagePoint) {
        DamagePoint = damagePoint;
    }

    public void Creature() {
    }
    public void Creature(int hp,int mp,int hpadd,int mpadd,int damagePoint){
    this.DamagePoint=damagePoint;
    this.Hp=hp;
    this.Mp=mp;
    this.Hpadd=hpadd;
    this.Mpadd=mpadd;
    }
    public Attribute(int HP, int MP, String name) {
        this.Hp = HP;
        this.Mp = MP;
        this.name = name;}

    public void attack(Attribute Role,Attribute Enemy){
        Enemy.setHp(Enemy.getHp()+Role.getDamagePoint());
    }

    public abstract int getDamagepoints();
}