package game.swimming.strokes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class freestyle implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
<<<<<<< Updated upstream
=======
        if (e.getKeyCode() == KeyEvent.VK_UP)
            System.out.println("숨 게이지 올리기");
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            System.out.println("아래로");
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            System.out.println("왼쪽");
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            System.out.println("오른쪽");
>>>>>>> Stashed changes

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    /* 자유형 : 좌우키 스페이스 숨
        배영 : 좌우키 스페이스 (반전)
        접영 : 스페이스 양쪽 위 아래 숨
        평영 : 위 양쪽 스페이스 아래 숨

        시작 시 3 2 1 스페이스 연타 ?
     */

}
