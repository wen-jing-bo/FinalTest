package com.xiaowen;

import com.xiaowen.Attribute.Attribute;
import com.xiaowen.Skill.SkillSet;

public class Role {
    private static volatile Role instance=null;

    private Role(){}

    public static Role getInstance(){
        if(instance==null){
            synchronized (Role.class){
                if(instance==null){
                    instance=new Role();
                }
            }
        }
        return instance;
    }

    private int HP;
    private int MP;
    private int HPmax;
    private int MPmax;
    private int damagepoints;
    private int HPadd;
    private int MPadd;
    private String name;
    private String occupation;
    SkillSet skills=new SkillSet();

    public SkillSet getSkills() {
        return skills;
    }

    public void setSkills(SkillSet skills) {
        this.skills = skills;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }


    public void attack(Attribute enemy){
        enemy.setHp(enemy.getHp()+this.damagepoints);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getHPmax() {
        return HPmax;
    }

    public void setHPmax(int HPmax) {
        this.HPmax = HPmax;
    }

    public int getMPmax() {
        return MPmax;
    }

    public void setMPmax(int MPmax) {
        this.MPmax = MPmax;
    }

    public int getDamagepoints() {
        return damagepoints;
    }

    public void setDamagepoints(int damagepoints) {
        this.damagepoints = damagepoints;
    }

    public int getHPadd() {
        return HPadd;
    }

    public void setHPadd(int HPadd) {
        this.HPadd = HPadd;
    }

    public int getMPadd() {
        return MPadd;
    }

    public void setMPadd(int MPadd) {
        this.MPadd = MPadd;
    }
}