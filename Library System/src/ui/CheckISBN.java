package ui;

public class CheckISBN {
	static String checkISBN(String ISBN) {
		boolean flag = true;
		if(ISBN.length()!=21) return "ISBN长度错误！";
		if(!ISBN.substring(0, 4).equals("ISBN")) return "请以'ISBN'开头";
		for(int i = 4;i < 21 ;i ++) {
			if((i == 7 || i == 9 || i == 14 || i == 19) && ISBN.charAt(i) == '-') {
				
			}else if(ISBN.charAt(i) <= '9' && ISBN.charAt(i) >= '0') {
				
			}else {
				return "ISBN格式不正确！";
			}
		}
		return "success";
	}
	
	
	public static void main(String[] args) {
		System.out.println(checkISBN("ISBN123-7-6511-6595-2"));
	}
}
