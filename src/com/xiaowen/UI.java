package com.xiaowen;

import Porp.Potion;
import Porp.PotionSet;
import com.xiaowen.Attribute.*;
import com.xiaowen.Skill.Skill;
import com.xiaowen.method.*;
import com.xiaowen.UI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class UI {


    private Place currentPlace;
    private HashMap<String, Methods> methods = new HashMap<String, Methods>();
    private Minion minion = new Minion();
    private Popo popo = new Popo();
    private Freeza freeza=new Freeza();
    private PotionSet dropPotion = new PotionSet();
    private MonsterSet monsters = new MonsterSet();
    boolean newcity = true;
    boolean newcrown = true;
    boolean newtrunk = true;

    public UI() {
        methods.put("go", new MethodGo(this));
        methods.put("attack", new MethodAttack(this));
        methods.put("exit", new MethodExit(this));
        methods.put("chat", new MethodChat(this));
        methods.put("use", new MethodUse(this));
        methods.put("check", new MethodCheck(this));
        methods.put("skill", new MethodSkill(this));
        currentPlace();
    }

    private void currentPlace() {
    }

    public void createPlace() {
        Place tongytianRiver, ImpregnablePass, nanjiangCity, taibaiVolcano;


        tongytianRiver = new Place("通天河");
        ImpregnablePass = new Place("通天河天关");
        nanjiangCity = new Place("南江城");
        taibaiVolcano = new Place("太白火山");

        taibaiVolcano.setHasSuperWeapon(true);

        tongytianRiver.setExit("south", ImpregnablePass);
        ImpregnablePass.setExit("north", tongytianRiver);
        ImpregnablePass.setExit("south", nanjiangCity);
        nanjiangCity.setExit("north", ImpregnablePass);
        nanjiangCity.setExit("west", taibaiVolcano);
        taibaiVolcano.setExit("east", nanjiangCity);
        currentPlace = nanjiangCity;

    }

    public MonsterSet getMonsters() {
        return monsters;
    }

    public String rollAttackMethod() {
        String Id = "";
        int ran = rollAttacknum();
        if (ran > 0 && ran <= 1) {
            Id = "普通攻击";
        } else if (ran > 1 && ran <= 3) {
            Id = "龟派气功";
        } else if (ran > 3 && ran <= 6) {
            Id = "太阳拳";
        } else if (ran > 6 && ran <= 10) {
            Id = "奥义·龙拳！";
        }

        return Id;
    }


    public int rollAttacknum() {
        int max = 10, min = 1;
        int ran = (int) (Math.random() * (max - min) + min);

        return ran;
    }


    public void createCrown(Place place) {
        Place immortalCrown;
        immortalCrown = new Place("不死树冠");

        immortalCrown.setExit("down", place);
        place.setExit("up", immortalCrown);
    }

    public void createTrunk(Place place) {
        Place immortalTrunk;
        immortalTrunk = new Place("不死树干");

        immortalTrunk.setExit("west", place);
        place.setExit("east", immortalTrunk);

    }

    public void createCity(Place place) {
        Place anCity;
        anCity = new Place("安城");


        anCity.setExit("south", place);
        place.setExit("north", anCity);
    }

    public void createNewScence() {
        int river = 0, city = 0, trunk = 0;
        for (Monster item : monsters.getMonsters()) {
            if (item.getCurrentPName().equals("安城")) {
                city += 1;
            } else if (item.getCurrentPName().equals("通天河")) {
                river += 1;
            } else if (item.getCurrentPName().equals("不死树干")) {
                trunk += 1;
            }
        }
        if (newcity && river == 0) {
            createCity(currentPlace);
            newcity = false;
        } else if (newtrunk && city == 0) {
            createTrunk(currentPlace);
            newtrunk = false;
        } else if (newcrown && trunk == 0) {
            createCrown(currentPlace);
            newcrown = false;
        }
    }


    public void openingPrint() {
        System.out.println("【~~~~~~~~~~~~~~~~~~~~娜美克星篇~~~~~~~~~~~~~~~~~~】");
        System.out.println("【孙悟饭乘坐神仙当年来到地球所乘宇宙飞船前往娜美克星】");
        System.out.println("【为了使用那美克星上的龙珠复活在贝吉塔一役中战死的战士们】");
        System.out.println("【孙悟饭与克林（光头）背离家乡数万光年来到那美克星】");
        System.out.println("【前路灰暗，不知要遇到怎样的挑战.....】");
        System.out.println("【孙悟饭】：比克叔叔、天净饭叔叔、饺子,我会集齐龙珠复活你们的");
        System.out.println("【克林】：真不知道娜美克星是什么样子");
        System.out.println("【孙悟饭】：。。。。。");
        System.out.println("【克林】：。。。。。");
        System.out.println("输入‘1’选择角色孙悟饭");
        System.out.println("输入‘2’选择角色克林");
    }

    public void attack(String enemyId) {
        Role.getInstance().attack(this.getMonsters().get(enemyId));
        monsters.get(enemyId).attack();
        monsterDie(enemyId);
    }

    public void monsterDie(String enemyId) {
        if (monsters.get(enemyId).getHp() <= 0) {
            drop(monsters.get(enemyId));
            Level.getInstance().addExperience(monsters.get(enemyId).getExperience());
            System.out.println("敌人" + monsters.get(enemyId).getId() + " 已被消灭！");
            Iterator<Monster> iterator = monsters.getMonsters().iterator();
            while (iterator.hasNext()) {
                Monster it = iterator.next();
                if (it.getId().equals(enemyId)) {
                    iterator.remove();
                }
            }
        }

    }

    public void printExit() {
        System.out.println("你现在在：" + currentPlace);
        System.out.println("你可以去的地方有：");
        System.out.println(currentPlace.getExitDirt());
    }

    public void MonsterDisplay(Place place) {
        int numbers = 0;
        System.out.println("这里的怪物有:");
        for (Monster item : monsters.getMonsters()) {
            if (item.getCurrentPName().equals(currentPlace.getName())) {
                System.out.println(item.getName() + " id:" + item.getId() + " HP:" + item.getHp());
                numbers += 1;
            }
        }
        if (numbers == 0) {
            System.out.println("此地无怪物出没");
        }
    }

    public void game() {

        System.out.println("请输入你的名字：");
        Scanner input = new Scanner(System.in);
        String name=input.nextLine();
        Role.getInstance().setName(name);
        System.out.println("开始游戏吧\n\n");

        while (true) {
            createNewScence();
            upgrade();

            roleCondition();
            printExit();
            MonsterDisplay(currentPlace);
            String sentence = input.nextLine();
            String[] words = sentence.split(" ");
            String value = "";
            Methods way = methods.get(words[0]);
            if (sentence.length() > 1)
                value = words[1];
            if (words[0].equals("help")) {
                printHelp();
            }
            if (way != null) {
                way.doCmd(value);
                if (way.isExit()) {
                    break;
                }
            }
            if (currentPlace.getName().equals("飞船降落地"))
                bossSkillAttackPerRound();
            if (Role.getInstance().getHP() <= 0) {
                System.out.println("" + Role.getInstance().getName() + "倒地，再起不能");
                break;
            }
        }

    }

    public void upgrade() {
        if (Level.getInstance().getLevel() > Level.getInstance().getBeforeLevel()) {
            int difference = Level.getInstance().getLevel() - Level.getInstance().getBeforeLevel();
            Role.getInstance().setHPmax(Role.getInstance().getHPmax() + Role.getInstance().getHPadd() * difference);
            Role.getInstance().setHP(Role.getInstance().getHPmax());
            Role.getInstance().setMPmax(Role.getInstance().getMPmax() + Role.getInstance().getMPadd() * difference);
            Role.getInstance().setMP(Role.getInstance().getMPmax());
            Level.getInstance().setBeforeLevel(Level.getInstance().getLevel());
        }
    }

    public void printHelp() {
        System.out.println("可用指令 change，attack，go，use,exit,skill");
        System.out.println("go: 例如 go east");
        System.out.println("attack: attack  敌人id");
        System.out.println("use（释放技能）: use 技能");
        System.out.println("skill 技能Id 释放技能");
        System.out.println("exit 退出");

    }

    public void bossSkillAttackPerRound() {
        String method = rollAttackMethod();
        System.out.println("弗利沙对你使出了 " + method);
        for (Skill item : freeza.getSkills().getSkills()) {
            if (method.equals(item.getId())) {
                Role.getInstance().setHP(Role.getInstance().getHP() + item.getDamagepoints());
            }
        }


    }

    public void drop(Attribute attribute) {
        int num = roll();
        createDropThings();
        if (attribute.getName().equals("小喽啰")) {
            if (num > 0 && num <= 30) {
                System.out.println("掉落 蓝药（恢复MP50）");
                Bag.getInstance().getPotions().getPotions().add(this.getPotionDrop().get("mppill"));
            } else if (num > 30 && num <= 60) {
                System.out.println("掉落 红药（恢复HP50）");
                Bag.getInstance().getPotions().getPotions().add(this.getPotionDrop().get("hppill"));
            } else {
                System.out.println("掉落，无");
            }
        } else {
            if (num == 1 || num == 100) {
                System.out.println("掉落 仙豆");
                Bag.getInstance().getPotions().getPotions().add(this.getPotionDrop().get("immortalpill"));
            }
        }

    }

    public PotionSet getPotionDrop() {
        return dropPotion;
    }

    public void createDropThings() {
        Potion hpPill = new Potion();
        Potion mpPill = new Potion();
        Potion immortalPill = new Potion();
        hpPill.setHPrecovery(50);
        hpPill.setNum(1);
        hpPill.setId("hppill");
        hpPill.setDescription("小红药");
        mpPill.setNum(1);
        mpPill.setId("mppill");
        mpPill.setDescription("小蓝药");
        immortalPill.setNum(1);
        immortalPill.setId("immortalpill");
        immortalPill.setDescription("仙豆儿");
        this.getPotionDrop().getPotions().add(hpPill);
        this.getPotionDrop().getPotions().add(mpPill);
        this.getPotionDrop().getPotions().add(immortalPill);
    }

    public int roll() {
        int max = 100;
        int min = 1;
        int ran = (int) (Math.random() * (max - min) + min);
        return ran;
    }

    public void printBag() {
        System.out.println("药品:");
        if (Bag.getInstance().getPotions().getPotions().size() != 0)
            for (Potion item : Bag.getInstance().getPotions().getPotions())
                System.out.println(item.getDescription() + " Id:" + item.getId() + " num:" + item.getNum());
        else {
            System.out.println("暂无药品");
        }
    }

    public void Role() {
        System.out.println("-------角-色-状-态-----------------");
        System.out.println("name:" + Role.getInstance().getName());
        System.out.println("level:" + Level.getInstance().getLevel());
        System.out.println("HP:" + Role.getInstance().getHP());
        System.out.println("MP:" + Role.getInstance().getMP());
        System.out.println("damagepoints:" + (Role.getInstance().getDamagepoints()));
    }

    public void printSkill() {
        for (Skill item : Role.getInstance().getSkills().getSkills()) {

            if (!item.getAttackRange().equals("treatment")) {
                System.out.println("Id:" + item.getId() + " manacost:" + item.getMpCost() + " damage:" + item.getDamagepoints() + " attackRange:" + item.getAttackRange());
            } else {
                System.out.println("Id:" + item.getId() + " manacost:" + item.getMpCost() + " hpTreat:" + item.getHpTreat());
            }
        }
    }

    public void goPlace(String direction) {
        Place nextPlace = currentPlace.getExit(direction);

        if (nextPlace == null) {
            System.out.println("那里不能前进！");
        } else {
            currentPlace = nextPlace;
        }
    }

    public void useSkill(String word) {
        boolean exist = false;
        for (Skill item : Role.getInstance().getSkills().getSkills()) {
            if (word.equals(item.getId())) {

                if (Role.getInstance().getMP() + item.getMpCost() >= 0) {
                    System.out.println("你使出了" + item.getId());
                    Role.getInstance().setMP(Role.getInstance().getMP() + item.getMpCost());
                    if ("groupAttack".equals(item.getAttackRange())) {
                        for (Monster object : monsters.getMonsters()) {
                            if (currentPlace.getName().equals(object.getCurrentPName())) {
                                object.setHp(object.getHp() + item.getDamagepoints());
                            }
                        }
                        for (int i = 0; i < monsters.getMonsters().size(); i++) {
                            for (Monster nObject : monsters.getMonsters()) {
                                if (nObject.getHp() <= 0) {
                                    monsterDie(nObject.getId());
                                    break;
                                }
                            }
                        }
                    } else if ("singleAttack".equals(item.getAttackRange())) {
                        System.out.println("输入敌人ID");
                        Scanner id = new Scanner(System.in);
                        String enemyId = id.nextLine();
                        for (Monster object : monsters.getMonsters()) {
                            if (enemyId.equals(object.getId())) {
                                object.setHp(object.getHp() + item.getDamagepoints());
                                if (object.getHp() > 0) {
                                    Role.getInstance().setHP(Role.getInstance().getHP() + object.getDamagepoints());
                                }
                            }
                        }
                        for (int i = 0; i < monsters.getMonsters().size(); i++) {
                            for (Monster nObject : monsters.getMonsters()) {
                                if (nObject.getHp() <= 0) {
                                    monsterDie(nObject.getId());
                                    break;
                                }
                            }
                        }
                    } else if ("treatment".equals(item.getAttackRange())) {
                        if (item.getHpTreat() + Role.getInstance().getHP() > Role.getInstance().getHPmax()) {
                            Role.getInstance().setHP(Role.getInstance().getHPmax());
                        } else {
                            Role.getInstance().setHP(item.getHpTreat() + Role.getInstance().getHP());
                        }
                    }

                } else {
                    System.out.println("法力值不足,无法释放!");
                }
            } else {
                exist = true;
            }
        }


        if (!exist) {
            System.out.println("此技能不存在!");
        }
    }


    public void usePotion(String word) {
        boolean exist = true;
        for (Potion item : Bag.getInstance().getPotions().getPotions()) {
            if (item.getId().equals(word)) {
                if (item.getHPrecovery() + Role.getInstance().getHP() > Role.getInstance().getHPmax())
                    Role.getInstance().setHP(Role.getInstance().getHPmax());
                else
                    Role.getInstance().setHP(Role.getInstance().getHP() + item.getHPrecovery());
                if (item.getMPrecovery() + Role.getInstance().getMP() > Role.getInstance().getMPmax())
                    Role.getInstance().setMP(Role.getInstance().getMPmax());
                else
                    Role.getInstance().setMP(Role.getInstance().getMP() + item.getMPrecovery());
                exist = false;
                break;
            }
        }
        Iterator<Potion> iterator = Bag.getInstance().getPotions().getPotions().iterator();
        while (iterator.hasNext()) {
            Potion it = iterator.next();
            if (it.getId().equals(word)) {
                iterator.remove();
                break;
            }
        }
        if (exist) {
            System.out.println("无此药品");
        }
    }

    public void roleCondition(){
        System.out.println("-------角-色-状-态-----------------");
        System.out.println("name:"+Role.getInstance().getName());
        System.out.println("level:"+Level.getInstance().getLevel());
        System.out.println("HP:"+Role.getInstance().getHP());
        System.out.println("MP:"+Role.getInstance().getMP());
        System.out.println("damagepoints:"+(Role.getInstance().getDamagepoints()));
    }
    public void createMonster(){
        freeza.setId("archmage");
        freeza.setName("弗利沙");
        freeza.setExperience(20000);
        freeza.setHp(2000);
        freeza.setMp(500000);
        freeza.setDamagePoint(-50);
        freeza.setCurrentPName("弗利沙的飞船");
        monsters.getMonsters().add(freeza);
        popo.setId("devil");
        popo.setName("Mr.popo");
        popo.setExperience(10000);
        popo.setHp(5000);
        popo.setMp(5000);
        popo.setDamagePoint(-30);
        popo.setCurrentPName("不死树干");
        monsters.getMonsters().add(popo);
        Minion A=new Minion();
        Minion B=new Minion();
        Minion C=new Minion();
        Minion D=new Minion();
        A.setId("red");
        A.setName("杂兵A");
        A.setExperience(500);
        A.setHp(400);
        A.setMp(2000);
        A.setCurrentPName("村落");
        A.setDamagePoint(-10);
        B.setId("green");
        B.setName("杂兵B");
        B.setExperience(500);
        B.setHp(500);
        B.setMp(1000);
        B.setCurrentPName("村落");
        B.setDamagePoint(-10);
        C.setId("purple");
        C.setName("杂兵C");
        C.setExperience(500);
        C.setHp(2000);
        C.setMp(1000);
        C.setCurrentPName("村落");
        C.setDamagePoint(-10);
        D.setId("blue");
        D.setName("杂兵D");
        D.setExperience(500);
        D.setHp(500);
        D.setMp(500);
        D.setCurrentPName("村落");
        D.setDamagePoint(-10);
        monsters.getMonsters().add(A);
        monsters.getMonsters().add(B);
        monsters.getMonsters().add(C);
        monsters.getMonsters().add(D);
    }
    public void createSkill(){
    Skill skill1=new Skill();
    }
    public void createRole(String line){

        if (line.equals("1")){
            Role.getInstance().setOccupation("孙悟饭");
            Role.getInstance().setHP(75);
            Role.getInstance().setHPmax(75);
            Role.getInstance().setMP(200);
            Role.getInstance().setMPmax(200);
            Role.getInstance().setHPadd(25);
            Role.getInstance().setMPadd(100);
            Role.getInstance().setDamagepoints(-75);
        }
        else if(line.equals("2")){
            Role.getInstance().setOccupation("克林");
            Role.getInstance().setHP(200);
            Role.getInstance().setHPmax(200);
            Role.getInstance().setMP(100);
            Role.getInstance().setMPmax(100);
            Role.getInstance().setHPadd(75);
            Role.getInstance().setMPadd(50);
            Role.getInstance().setDamagepoints(-30);
        }
        else{
            System.out.println("无此选项，请重新选择：");
            Scanner it = new Scanner(System.in);
            String choose=it.nextLine();
            createRole(choose);
        }


    }
    public void play() {
        createMonster();
        createSkill();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        createRole(line);
        game();
        in.close();
    }
}