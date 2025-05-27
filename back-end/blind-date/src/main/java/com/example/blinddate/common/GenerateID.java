package com.example.blinddate.common;

public class GenerateID {
    static int temp = 0;
    public static String generateID(){
        String str = "";
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0;i < 6;i++){
            temp = (int) (Math.random()*10)+1;
            if(i == 0){
                stringBuffer.append(String.valueOf((int) (Math.random()*10)));
            }else {
                if(temp != 10){
                    stringBuffer.append(String.valueOf(temp));
                }else {
                    while (true) {
                        if (temp != 10){
                            stringBuffer.append(String.valueOf(temp));
                            break;
                        }else {
                            temp = (int) (Math.random() * 10) + 1;
                        }
                    }
                }
            }
        }
        return stringBuffer.toString();
    }

}
