package day0317;

import java.util.ArrayList;
import java.util.Scanner;

import day0316.Student;
import util.ScannerUtil;

public class GradeBookReview {
    private static ArrayList<Student> list = new ArrayList<>();
    private static final int SCORE_MIN = 0;
    private static final int SCORE_MAX = 100;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu();
    }

    private static void showMenu() {
        while (true) {
            System.out.println("============================");
            System.out.println("           비트교육           ");
            System.out.println("        성적관리 프로그램       ");
            System.out.println("=============================");

            String message = new String("1. 정보 입력 2. 목록 보기 3.종료");
            int userChoice = ScannerUtil.nextInt(scanner, message);
            if (userChoice == 1) {
                insert();

            } else if (userChoice == 2) {
                printAll();

            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private static void insert() {
        Student s = new Student();
        String message = new String("번호를 입력해주세요");
        s.setId(ScannerUtil.nextInt(scanner, message));

        message = new String("이름를 입력해주세요");
        s.setName(ScannerUtil.nextLine(scanner, message));
        
        message = new String("국어 점수를 입력해주세요");
        s.setKorean(ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));
        
        message = new String("영어 점수를 입력해주세요");
        s.setEnglish(ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));
        
        message = new String("수학 점수를 입력해주세요");
        s.setMath(ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));
        
       list.add(s);

    }
    
    private static void printAll() {
        while(true) {
            if(list.size() == 0) {
                System.out.println("아직 입력된 정보가 없습니다.");
                break;
            }
            
           for (int i = 0; i < list.size(); i++) {
            System.out.printf("%2d %s\n", i+1,list.get(i).getName());
        }
           String message = new String("개별보기 할 학생의 번호를 선택해주세요. 뒤로가기는 0번 입니다.");
           int userChoice = ScannerUtil.nextInt(scanner, message, 0, list.size())-1;
            
           if(userChoice == -1) {
               System.out.println();
               System.out.println("메뉴로 돌아갑니다");
               System.out.println();
           }else {
               
           }
        }
    }
    private static void printOne(int index) {
        list.get(index).showInfo();
        
        String message = new String(" 1. 수정 2. 삭제 3. 돌아가기");
        int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
        
        if(userChoice == 1) {
            update(index);
            printOne(index);
            
        }else if(userChoice == 2) {
            delete(index);
        }
        
    }
    
    private static void update(int index) {
        String message = new String("국어 점수를 입력해주세요");
        list.get(index).setKorean(ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));
        
        message = new String("영어 점수를 입력해주세요");
        list.get(index).setEnglish(ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));
        
        message = new String("수학 점수를 입력해주세요");
        list.get(index).setMath(ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));
        
    }
    
    private static void delete(int index) {
        String message = new String(" 정말로 삭제 하시겠습니까? 삭제하시려면 Y 혹은 y를 입력해주세요.");
        String yesNo = ScannerUtil.nextLine(scanner, message);
        
        if(yesNo.equalsIgnoreCase("Y")) {
            list.remove(index);
        }else {
            printOne(index);
        }
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    

}
