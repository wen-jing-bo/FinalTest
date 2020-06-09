package com.xiaowen.Skill;

public class Skill {
   private String name;
   private int damagePoint;
   private int mpCost;
   private int hpTreat;
   private String attackRange;
   private String id;

public Skill(String name,int damagePoint,int mpCost,String attackRange){
this.name=name;
this.damagePoint=damagePoint;
this.mpCost=mpCost;
this.attackRange=attackRange;
}

   public Skill() {

   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getDamagePoint() {
      return damagePoint;
   }

   public void setDamagePoint(int damagePoint) {
      this.damagePoint = damagePoint;
   }

   public int getMpCost() {
      return mpCost;
   }

   public void setMpCost(int mpCost) {
      this.mpCost = mpCost;
   }

   public int getHpTreat() {
      return hpTreat;
   }

   public void setHpTreat(int hpTreat) {
      this.hpTreat = hpTreat;
   }

   public void setAttackRange(String attackRange) {
      this.attackRange = attackRange;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Skill(String name, int hpTreat, int mpCost){
   this.name=name;
   this.hpTreat=hpTreat;
   this.mpCost=mpCost;
}

   public String getId() {
      return id;
   }
   public int getDamagepoints() {
      return damagePoint;
   }

   public String getAttackRange() {
      return attackRange;
   }
}
