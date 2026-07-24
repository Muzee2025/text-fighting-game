import com.itmuzee.ui.FightingGame;
import com.itmuzee.ui.Login;

public class App {
    public static void main(String[] args) {

        /*启动类
        不写核心代码，只保留启动类*/
        /*Login l = new Login();
        l.start();*/

        FightingGame fg = new FightingGame();
        fg.gameStart("zhangsan");
        //创建玩家的角色，属性分配
    }

    public void createPlayerCharacter(String username){

    }
}
