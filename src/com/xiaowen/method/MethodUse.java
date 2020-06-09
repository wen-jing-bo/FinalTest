package com.xiaowen.method;

import com.xiaowen.Methods;
import com.xiaowen.UI;

public class MethodUse extends Methods {
    public MethodUse(UI ui){
        super(ui);
    }

    public void doCmd(String word){
        ui.usePotion(word);
    }
}
