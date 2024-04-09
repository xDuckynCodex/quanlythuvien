/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.dao.RenterDao;
import quanlythuvien.entities.Renter;
import quanlythuvien.views.ManageView;
import quanlythuvien.views.RenterView;

/**
 *
 * @author Admin
 */
public class RenterController {
    private RenterDao renterDao;
    private PublicationDao publicationDao;

    public void setRenterDao(RenterDao renterDao) {
        this.renterDao = renterDao;
    }

    public void setPublicationDao(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

    public void setManageView(ManageView manageView) {
        this.manageView = manageView;
    }

    private RenterView renterView;
    private ManageView manageView;

    public void setManageController(ManageController manageController) {
        this.manageController = manageController;
    }

    private ManageController manageController;

    public RenterController(RenterView view){
        this.renterView = view;
//        view.hideTablePub();
        view.addAddRenterListener(new AddRenterListener());
        view.addEditRenterListener(new EditRenterListener());
        view.addDeleteRenterListener(new DeleteRenterListener());
        view.addSortByNameRenterListener(new SortByNameRenterListener());
        view.addClearRenterListener(new ClearRenterListener());
        view.addFillRenterFromSelectedRow(new FillRenterFromSelectedRowListener());
        view.addTransferPublicationListener(new TransferPublicationListener());
        view.addRentedBookFieldSearch(new addRentedBookSearchListener());
    }
    
    public void showRenterView(){
        renterView.setVisible(true);
        renterView.showListRenter(renterDao.getListRenter());
        renterView.clear();
    }
    
    class AddRenterListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Renter renter = renterView.getRenterInfo();
            if(renter != null){
                renterDao.addRenter(renter);
                renterView.showRenter(renter);
                renterView.showListRenter(renterDao.getListRenter());
                renterView.clear();
                renterView.showMessage("Thêm thành công");
            }
        }
    }
    
    class EditRenterListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Renter renter = renterView.getRenterInfo();
            if(renter != null){
                renterDao.editRenter(renter);
                renterView.showRenter(renter);
                renterView.showListRenter(renterDao.getListRenter());
                renterView.clear();
                renterView.showMessage("Cập nhật thành công");
            }
        }
    }
     class DeleteRenterListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Renter renter = renterView.getRenterInfo();
            if(renter != null){
                renterDao.delete(renter);
                renterView.showRenter(renter);
                renterView.showListRenter(renterDao.getListRenter());
                renterView.showMessage("Xoá thành công");
            }
        }
    }
    class FillRenterFromSelectedRowListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            renterView.fillRenterFromSelectedRow();
        }
    }

    class ClearRenterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            renterView.clear();
        }
    }
    
    class TransferPublicationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            renterView.setVisible(false);
            manageController.showView();
        }
    }

    class SortByNameRenterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            renterDao.sortByName();
            renterView.showListRenter(renterDao.getListRenter());
        }
    }
    
    class addRentedBookSearchListener implements DocumentListener{
        public void insertUpdate(DocumentEvent e) {
            String searchRentedBookField = renterView.getSearchRentedBookField();
            renterView.showResultView(searchRentedBookField);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            String searchRentedBookField = renterView.getSearchRentedBookField();
            if("".equals(searchRentedBookField)){
                renterView.hideTablePub();
            } else{
                renterView.showResultView(searchRentedBookField);
            }       
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }
    
    public static void main(String[] args){
        RenterView rv = new RenterView();
        RenterController rc = new RenterController(rv);
        rc.showRenterView();
    }
}
