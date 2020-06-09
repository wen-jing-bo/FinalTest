package com.xiaowen.method;

import com.xiaowen.Methods;
import com.xiaowen.UI;

public class MethodCheck extends Methods {

    public MethodCheck(UI ui) {
        super(ui);
    }

    public void doCmd(String word){
        if(word.equals("bag")){
            ui.printBag();
        }

        else if(word.equals("skill")){
            ui.printSkill();
        }
        else{
            System.out.println("未命名命令，无法有效执行");
        }
    }
}
