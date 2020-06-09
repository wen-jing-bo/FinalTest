package com.xiaowen.Attribute;
import com.xiaowen.Role;
import com.xiaowen.Skill.SkillSet;

public class Freeza extends Monster{
    public SkillSet getSkills() {
        return skills;
    }

    public Freeza(int HP, int MP, String name, int experience) {
        super(HP, MP, name, experience);
    }
    SkillSet skills=new SkillSet();

    public Freeza() {

    }



    public void setSkills(SkillSet skills) {
        this.skills = skills;
    }
}
