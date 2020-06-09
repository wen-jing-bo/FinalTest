package com.xiaowen.method;

import com.xiaowen.Methods;
import com.xiaowen.UI;
import com.xiaowen.UI;

public class MethodAttack extends Methods {

        public MethodAttack(UI ui){
            super(ui);
        }

        public void doCmd(String word){
            ui.attack(word);
        }

}
