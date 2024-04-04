package quanlythuvien.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class ContextMenu extends JPopupMenu {
    public ContextMenu() {
        initComponent();
    }
    private JMenuItem editMenuItem, deleteMenuItem;
    private InfoView infoView;
    public void initComponent() {
        editMenuItem = new JMenuItem("Edit");
        editMenuItem.addActionListener(new EditMenuItem());
        deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(new DeleteMenuItem());
        this.add(editMenuItem);
        this.add(deleteMenuItem);
    }

    class EditMenuItem implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            infoView = new InfoView();
        }
    }

    class DeleteMenuItem implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}

