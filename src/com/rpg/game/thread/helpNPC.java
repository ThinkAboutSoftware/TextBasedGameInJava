package com.rpg.game.thread;

public class helpNPC implements Runnable {


    @Override
    public void run() {
        // 캐릭터 레벨 가져오기
        // 레벨이 올라가면 도움을 준다.
        chat();
    }

    public void chat() {
        System.out.println("Level Up을 축하합니다. 저는 레벨이 올라갈 때마다 나타나는 '도움 NPC' 입니다. 무엇을 도와드릴까요?");
    }
}
