package product;

import java.sql.*;

import lombok.Data;

@Data
public class ProductDTO {
	
	private int p_id;
	private String name;
	private int price;
	private String content;
	private Date regdate;
	


}
//create table product (
//	    p_id number(5)  not null primary key,  
//	    name varchar2(255) not null,              
//	    price number(8,2) not null,                    
//	    content varchar2(2000),                        
//	    regdate date default sysdate              
//	    );