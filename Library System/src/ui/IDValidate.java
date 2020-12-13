package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 *
 * ���֤18λ�ֱ����ĺ��壬���֤15λ������18λ��ԭ������2λ��û�����һλ�������ҷ��ֱ��ʾ
 * ��1-2 ��������������
 * ��3-4 �ؼ����������ִ���
 * ��5-6 �����������ִ���
 * ��7-10 11-12 13-14 �����ꡢ�¡���
 * ��15-17 ˳���룬ͬһ����ͬ�ꡢͬ�¡�ͬ�ճ����˵ı�ţ����������ԣ�ż����Ů��
 * ��18 У���룬�����0-9����0-9��ʾ�������10����X����������10����ʾ(���һλ���ܳ��ֵ�X������Ӣ����ĸ�أ�����ϣ������10����дX)
 *
 * ֻҪ��ÿλ�Ķ�ӦȨ�س���ÿ��λ�ϵ���ֵ��Ȼ����ͣ������11���������õ��Ľ���Ա��ҵ�β�����ɡ�
 *
 * ԭ��15λ���֤��û��У��λ�ģ�ͬʱ���õ���2λ��������ʾ�������
 *
 *
 *
 * ��һ����
 * �ȶ�ǰ17λ���ֵ�Ȩ��� ,ʹ��ʮ��λ���ֱ������Ȩ��͹�ʽ   S = Sum(Ai * Wi), i = 0, ... , 16
 * Ai:��ʾ��iλ���ϵ����֤��������ֵ(0~9)
 * Wi:7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 ���̶��ģ���ʾ��iλ���ϵļ�Ȩ���ӣ�
 * �����֤����ĵ�1λ������7��ˣ�
 * �����֤����ĵ�2λ������9��ˣ�
 * �����֤����ĵ�3λ������10��ˣ�
 * �����֤����ĵ�4λ������5��ˣ�
 * �����֤����ĵ�5λ������8��ˣ�
 * �����֤����ĵ�6λ������4��ˣ�
 * �����֤����ĵ�7λ������2��ˣ�
 * �����֤����ĵ�8λ������1��ˣ�
 * �����֤����ĵ�9λ������6��ˣ�
 * �����֤����ĵ�10λ������3��ˣ�
 * �����֤����ĵ�11λ������7��ˣ�
 * �����֤����ĵ�12λ������9��ˣ�
 * �����֤����ĵ�13λ������10��ˣ�
 * �����֤����ĵ�14λ������5��ˣ�
 * �����֤����ĵ�15λ������8��ˣ�
 * �����֤����ĵ�16λ������4��ˣ�
 * �����֤����ĵ�17λ������2��ˡ�
 *
 * �ڶ�����
 * ����һ�����֤����1~17λ��˵Ľ����ͣ�ȫ����������
 *
 * ��������
 * ����ģ  Y = mod(S, 11)
 * �õڶ�����������Ľ������11�������ͻ����
 * ����Ϊ0��
 * ����Ϊ1��
 * ����Ϊ2��
 * ����Ϊ3��
 * ����Ϊ4��
 * ����Ϊ5��
 * ����Ϊ6��
 * ����Ϊ7��
 * ����Ϊ8��
 * ����Ϊ9��
 * ����Ϊ10
 * ��11�ֿ����ԡ�
 *
 * ���Ĳ���
 * ����ģ�����ҵõ���Ӧ��У����
 * Y:    0 1 2 3 4 5 6 7 8 9 10
 * У����: 1 0 X 9 8 7 6 5 4 3 2
 * �������Ϊ0���Ƕ�Ӧ�����һλ���֤�ĺ���Ϊ1��
 * �������Ϊ1���Ƕ�Ӧ�����һλ���֤�ĺ���Ϊ0��
 * �������Ϊ2���Ƕ�Ӧ�����һλ���֤�ĺ���ΪX��
 * �������Ϊ3���Ƕ�Ӧ�����һλ���֤�ĺ���Ϊ9��
 * �������Ϊ4���Ƕ�Ӧ�����һλ���֤�ĺ���Ϊ8��
 * �������Ϊ5���Ƕ�Ӧ�����һλ���֤�ĺ���Ϊ7��
 * �������Ϊ6���Ƕ�Ӧ�����һλ���֤�ĺ���Ϊ6��
 * �������Ϊ7���Ƕ�Ӧ�����һλ���֤�ĺ���Ϊ5��
 * �������Ϊ8���Ƕ�Ӧ�����һλ���֤�ĺ���Ϊ4��
 * �������Ϊ9���Ƕ�Ӧ�����һλ���֤�ĺ���Ϊ3��
 * �������Ϊ10���Ƕ�Ӧ�����һλ���֤�ĺ���Ϊ2��
 *
 * @author WXW
 *
 */
public class IDValidate {
    
    static String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7","9", "10", "5", "8", "4", "2" };//ʮ��λ���ֱ�����Ȩ��
    
    static String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4","3", "2" }; //���һλ��У�����ַ�ֵ    
    

    
    /***
     * ��֤���֤
     * @param idStr
     * @return
     */
    public static boolean checkID(String idStr,Date input_birth){
        try {
            if(null == idStr){// ��֤�ǿ�
                System.out.println("���֤���벻��Ϊ��");
                return false;
            }
            if(idStr.length() != 15 && idStr.length() != 18){// ֻ����15λ����18λ
                System.out.println("���֤���볤��ֻ����15λ����18λ");
                return false;
            }
            
             String Ai = "";
             if (idStr.length() == 18) {
                Ai = idStr.substring(0, 17);
            }
            
             if (idStr.length() == 15) {//  ��15λ���֤ת��Ϊ 17λ���֤�����������һλ��ת��Ϊ18λ���֤
                // 15λ���֤��û��У��λ�ģ�ͬʱ���õ���2λ��������ʾ������ݣ����� 15λ�����֤����ȷ������19**���
                Ai = idStr.substring(0, 6) + "19" + idStr.substring(6, 15);
            }
            if(!isNumber(Ai)){ // ��֤���֤ǰ17λ�Ƿ�������
                System.out.println("���֤15λ���붼ӦΪ���� ; 18λ��������һλ�⣬��ӦΪ���֡�");
                return false;
            }
            
            int year = Integer.parseInt(Ai.substring(6, 10));// �������
            int month = Integer.parseInt(Ai.substring(10, 12));// �����·�
            int day = Integer.parseInt(Ai.substring(12, 14));// ��������
            String myBirth =Ai.substring(6, 10)+"-"+Ai.substring(10, 12)+"-"+Ai.substring(12, 14);
            String birthDay = year+"-"+month+"-"+day;
            
            Date birthdate = null;
            try {// ����������ת��ΪDate����
            	SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
            	String  dString = formatter.format(input_birth);

            	if(!myBirth.equals(dString)) {
            		System.out.println("���֤��������ڲ���");
            		return false;
            	}
            	
                birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthDay);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("���֤������Ч��");
                return false;
            }
            if (birthdate == null || new Date().before(birthdate)) {
                System.out.println("���֤������Ч��");
                return false;
            }
            
            GregorianCalendar gc = new GregorianCalendar();//GregorianCalendar �� Calendar ��һ���������࣬�ṩ�������ϴ��������/����ʹ�õı�׼����ϵͳ��
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            // ��֤��������Ƿ��ڷ�Χ֮��
            if ((gc.get(Calendar.YEAR) - year) > 150 || (gc.getTime().getTime() - s.parse(birthDay).getTime()) < 0) {
                System.out.println("���֤���ղ�����Ч��Χ��");
                return false;
            }
            
            //��֤�·�
            if (month > 12 || month <= 0) {
                System.out.println("���֤���е��·���Ч");
                return false;
            }
            
            //��֤����
            gc.setTime(birthdate);
            boolean mflag = false;
            switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                mflag = (day >= 1 && day <= 31);
                break;
            case 2: // ������2�·�������28��,�����2����29�졣
                if (gc.isLeapYear(gc.get(Calendar.YEAR))) {
                    mflag = (day >= 1 && day <= 29);
                } else {
                    mflag = (day >= 1 && day <= 28);
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                mflag = (day >= 1 && day <= 30);
                break;
            }
            if (!mflag) {// ���ڲ���
                System.out.println("ʡ��֤���еĳ������ڲ���");
                return false;
            }
            // ��֤ ��ͷ��λ���Ƿ�����ʵ��Ч�ĵ�������
            if (cityCodeMap.get(Ai.substring(0, 2)) == null) {
                System.out.println("���֤�����������");
                return false;
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("��֤����");
            e.printStackTrace();
            return false;
        }
        
        
    }
    
    /***
     * ��������
     */
    private static Map<String, String> cityCodeMap = new HashMap<String, String>() {
        {
            this.put("11", "����");
            this.put("12", "���");
            this.put("13", "�ӱ�");
            this.put("14", "ɽ��");
            this.put("15", "���ɹ�");
            this.put("21", "����");
            this.put("22", "����");
            this.put("23", "������");
            this.put("31", "�Ϻ�");
            this.put("32", "����");
            this.put("33", "�㽭");
            this.put("34", "����");
            this.put("35", "����");
            this.put("36", "����");
            this.put("37", "ɽ��");
            this.put("41", "����");
            this.put("42", "����");
            this.put("43", "����");
            this.put("44", "�㶫");
            this.put("45", "����");
            this.put("46", "����");
            this.put("50", "����");
            this.put("51", "�Ĵ�");
            this.put("52", "����");
            this.put("53", "����");
            this.put("54", "����");
            this.put("61", "����");
            this.put("62", "����");
            this.put("63", "�ຣ");
            this.put("64", "����");
            this.put("65", "�½�");
            this.put("71", "̨��");
            this.put("81", "���");
            this.put("82", "����");
            this.put("91", "����");
        }
    };

    /***
     * �ж�str�Ƿ�Ϊ���������
     * @param str
     * @return
     */
    private static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}