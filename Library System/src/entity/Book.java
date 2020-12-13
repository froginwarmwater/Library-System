package entity;

import java.util.Date;
import java.util.List;

/*
 * 
 * 
 * book(*b_id,ISBN,state)
 * book_detail(*ISBN,name,catalog,publish_date,content,price,publish,author,number,left)
 * */
public class Book {
	private int b_id,number,left;
	private String ISBN,name,catalog,content,publisher,author;
	private Date publish_date,lend_date,return_date;
	private int is_due;
	private int state,lendTimes;
	
	public int getLendTimes() {
		return lendTimes;
	}
	public void setLendTimes(int lendTimes) {
		this.lendTimes = lendTimes;
	}
	private double price;
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCatalog() {
		return catalog;
	}
	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}




	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	public Book() {
		this.b_id = -1001;
		this.number = 1;
		this.left = 1;
		ISBN = null;
		this.name = null;
		this.catalog = null;
		this.content = null;
		this.publisher = null;
		this.author = null;
		this.publish_date = null;
		this.state = 1;
		this.is_due = 0;
		this.price = 0;
	}
	public Date getLend_date() {
		return lend_date;
	}
	public void setLend_date(Date lend_date) {
		this.lend_date = lend_date;
	}
	public Date getReturn_date() {
		return return_date;
	}
	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public int getIs_due() {
		return is_due;
	}
	public void setIs_due(int is_due) {
		this.is_due = is_due;
	}
	@Override
	public String toString() {
		return "Book [b_id=" + b_id + ", number=" + number + ", left=" + left + ", ISBN=" + ISBN + ", name=" + name
				+ ", catalog=" + catalog + ", content=" + content + ", publisher=" + publisher + ", author=" + author
				+ ", publish_date=" + publish_date + ", lend_date=" + lend_date + ", return_date=" + return_date
				+ ", is_due=" + is_due + ", state=" + state + ", price=" + price + "]";
	}


	
}
