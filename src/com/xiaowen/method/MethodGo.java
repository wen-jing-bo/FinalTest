package com.xiaowen.method;

import com.xiaowen.Methods;
import com.xiaowen.UI;
import com.xiaowen.UI;

public class MethodGo extends Methods {
    public  MethodGo(UI ui){
        super(ui);
    }
    public void doCmd(String word){ui.goPlace(word);}
}
