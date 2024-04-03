package quanlythuvien.dao;

import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import quanlythuvien.entities.Publication;
import quanlythuvien.utils.FileUtils;
import quanlythuvien.views.ManagerView;

public class PublicationDao {
    private static final String file_name = "Publication.xml";
    private List<Publication> listPub;
    List<Publication> pubFilter = new ArrayList<Publication>();

    public PublicationDao(){
        this.listPub = readPublication();
        if(listPub == null){
            listPub = new ArrayList<Publication>();
        }
    }
    
    // in ra file XML
    public void writeListPub(List<Publication> publications) {
        PublicationXML pubXML = new PublicationXML();
        pubXML.setPublication(publications);
        List<Publication> existingPublications = readPublication();       
        FileUtils.writeXMLtoFile(file_name, pubXML);
    }
    
    // doc file XML
    public List<Publication> readPublication() {
        List<Publication> list = new ArrayList<Publication>();
        PublicationXML pubXML = (PublicationXML) FileUtils.readXMLFile(
                file_name, PublicationXML.class);
        if (pubXML != null) {
            list = pubXML.getPublication();
        }
        return list;
    }
    
    // them sach
    public void add(Publication pub){
        listPub.add(pub);
    }
    
    // sua thong tin
    public void edit(Publication pub){
        for(int i = 0; i < listPub.size(); i++ ){
            if(Objects.equals(listPub.get(i).getCode(), pub.getCode())){
                listPub.get(i).setName(pub.getName());
                listPub.get(i).setType(pub.getType());
                listPub.get(i).setPublisher(pub.getPublisher());
                listPub.get(i).setAuthor(pub.getAuthor());
                listPub.get(i).setPrice(pub.getPrice());
                listPub.get(i).setQuantity(pub.getQuantity());
                listPub.get(i).setPublishedDate(pub.getPublishedDate());
            }
        }
    }
    
    // xoa sach
    public boolean delete(Publication pub){
        boolean check = false;
        for(int i = 0; i < listPub.size(); i++){
            if(Objects.equals(listPub.get(i).getCode(), pub.getCode())){
                pub = listPub.get(i);
                check = true;
                break;
            }
        }
        if(check){
            listPub.remove(pub);
            writeListPub(listPub);
            return true;
        }
        return false;
    }
  
    //sap xep theo ten
    public void sortByName(){
        Collections.sort(listPub, new Comparator<Publication>(){
            public int compare(Publication pub1, Publication pub2){
                return pub1.getName().compareTo(pub2.getName());
            }
        });
    }
    
    // sap xep theo gia ca
    public void sortByPrice(){
        Collections.sort(listPub, new Comparator<Publication>(){
            public int compare(Publication pub1, Publication pub2){
                if(pub1.getPrice() > pub2.getPrice()){
                    return 1;
                }
                return 0;
            }
        });
    }
    
    public List<Publication> filter(){
        pubFilter = listPub.stream().filter(publication -> publication.getType().equals("Tạp chí")).toList();
        return pubFilter;
    }
    
    public List<Publication> getListPublication(){
        return listPub;
    }
    public List<Publication> getListPubFilter(){
        return pubFilter;
    }
}
