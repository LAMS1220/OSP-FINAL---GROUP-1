
package osp.pkgfinal.group.pkg1;
import java.awt.*;

public class OSPMain {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
    }
    
}