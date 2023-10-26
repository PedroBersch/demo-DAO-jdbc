package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Department dp = new Department(1,"Books");
        Seller seller = new Seller(1,"Pedro", "pedro@gmail.com",sdf.parse("16/04/2003"), 2000.0,dp);
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println(dp);
        System.out.println(seller);
    }
}
