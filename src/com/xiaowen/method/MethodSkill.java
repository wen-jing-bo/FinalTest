package com.xiaowen.method;

import com.xiaowen.Methods;
import com.xiaowen.UI;

public class MethodSkill extends Methods {
    public MethodSkill(UI ui) {
        super(ui);
    }

    public void doCmd(String word){
        ui.useSkill(word);
    }
}
