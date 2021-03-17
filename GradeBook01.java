package day0317;

import java.util.ArrayList;
import java.util.Scanner;

import day0316.Student;
import util.ScannerUtil;

public class GradeBook01 {
    // 학생의 목록을 저장할 ArrayList 객체 List
    private static ArrayList<Student> list = new ArrayList<>();
    // 점수의 최소값을 가지고 있는 상수 SCORE_MIN
    private static final int SCORE_MIN = 0;
    // 점수의 최대값을 가지고 있는 상수 SCORE_MAX
    private static final int SCORE_MAX = 100;
    // 입력을 처리해줄 Scanner 객체 scanner
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu();
    }

    // 기존에는 우리가 이러한 메뉴를
    // 메인 메소드에 직접 적어놓았지만
    // 이제는 그러한 코드들도 모두 메소드를 분리하여
    // 개별적으로 처리가 될 수 있도록 바꿔준다.
    private static void showMenu() {
        while (true) {
            System.out.println("===============================");
            System.out.println("           비트고등학교           ");
            System.out.println("         성적 관리 프로그램        ");
            System.out.println("===============================");
            String message = new String("1. 입력\n2. 목록 보기\n3. 종료");
            int userChoice = ScannerUtil.nextInt(scanner, message);

            if (userChoice == 1) {
                // 입력 메소드 실행
                insert();

            } else if (userChoice == 2) {
                // 목록 보기 메소드 실행
                printAll();

            } else if (userChoice == 3) {
                // 종료 실행
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }

        }
    }

    // 입력을 담당하는 메소드
    private static void insert() {
        // 지금 현재 내키지 않지만
        // 학생의 목록을 보관하는 ArrayList 전역변수가 존재하므로
        // 우리는 여기서 학생 객체를 하나 만들어서
        // 정보를 입력해주고
        // 그리고 그 정보가 입력된 학생 객체를 ArrayList에 추가해주면 된다.

        // 리스트에 추가할 Student 객체 생성
        Student s = new Student();

        // 객체의 정보 입력
        String message = new String("학생의 번호를 입력해주세요");
        s.setId(ScannerUtil.nextInt(scanner, message));

        message = new String("학생의 이름을 입력해주세요");
        s.setName(ScannerUtil.nextLine(scanner, message));

        message = new String("학생의 국어 점수를 입력해주세요");
        s.setKorean(ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));

        message = new String("학생의 영어 점수를 입력해주세요");
        s.setEnglish(ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));

        message = new String("학생의 수학 점수를 입력해주세요");
        s.setMath(ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));

        // 정보가 입력된 객체를 우리의 리스트에 추가해준다.
        list.add(s);

    }

    private static void printAll() {
        // 목록을 쭉 출력하고
        // 사용자에게 개별 보기할 값을 입력받거나
        // 아니면 뒤로가기를 할 수 있도록 안내한다.

        // 만약 list의 size가 0이면 "아직 입력된 학생 정보가 존재하지 않습니다"가 출력이 되고
        // 그 외에는 개별 보기 후에 다시 여기에 오더라도 다시 목록을 볼 수 있도록
        // 무한루프를 만들어준다.

        while (true) {
            if (list.size() == 0) {
                System.out.println();
                System.out.println("아직 입력된 학생의 정보가 존재하지 않습니다.");
                System.out.println();
                break;
            }

            System.out.println();
            for (int i = 0; i < list.size(); i++) {
                System.out.printf(" %d. %s \n", i + 1, list.get(i).getName());
            }
            String message = new String("개별 보기할 학생의 번호를 선택해주세요. 뒤로가기는 0을 입력해주세요.");
            int userChoice = ScannerUtil.nextInt(scanner, message, 0, list.size()) - 1;

            // 사용자가 0을 입력하면 -1이 되서 userChoice에 저장되므로
            // userChoice 가 -1이면 break를 통해 목록 보는 것을 종료시킨다.
            // 그 외에는 해당 userChoice를 파라미터로 하는 개별 보기 메소드를 실행시켜준다.
            if (userChoice == -1) {
                System.out.println();
                System.out.println("메뉴로 돌아갑니다.");
                System.out.println();
                break;
            } else {
                // userChoice를 파라미터로 하는 개별보기 메소드
                // printOne 메소드를 실행해준다!
                printOne(userChoice);

            }

        }
    }

    private static void printOne(int index) {
        // 학생 개인의 showInfo() 메소드를 실행 후에
        // 수정, 삭제, 뒤로가기 중 하나를 입력 받는다.
        list.get(index).showInfo();
        String message = new String("1. 수정 2. 삭제 3. 뒤로가기");
        int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);

        // 사용자가 선택 가능한 값은 1~3이지만
        // 3을 선택할 시에는 아무런 처리도 해주지 않으면 메소드가 자동으로 종료되므로
        // 다시 목록보기로 돌아간다.
        if (userChoice == 1) {
            // 수정 메소드 실행
            update(index);
            printOne(index);
        } else if (userChoice == 2) {
            // 삭제 메소드 실행
            delete(index);
        }

    }
    // 수정 메소드 
    private static void update(int index) {
        // 우리가 받은 인데스를 그대로 get으로 해서
        // 국어, 영어, 수학 점수에 대한 setter를 실행시켜서
        // 새로운 값들을 넣어주면 된다.
        String message = new String("학생의 국어 점수를 입력해주세요");
        list.get(index).setKorean(ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));

        message = new String("학생의 영어 점수를 입력해주세요");
        list.get(index).setEnglish(ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));

        message = new String("학생의 수학 점수를 입력해주세요");
        list.get(index).setMath(ScannerUtil.nextInt(scanner, message, SCORE_MIN, SCORE_MAX));
        
    }
    
    // 삭제 메소드
    private static void delete(int index) {
        // 먼저 사용자로부터 정말로 삭제할지를 물어보고
        // 만약 사용자가 y 혹은 Y 외에 다른 값을 입력하면 삭제를 하지 않는다. 
        // 사용자가 정확하게 대소문자 y 를 입력할 때에만 삭제하고
        // 삭제되면 목록으로 돌아가게 된다. 
        // 삭제되지 않았을 때에는 여기서 printOne(index)를 실행시켜준다. 
        
        // 1. 사용자한테 동의할지 말지를 입력받는다.
        String message = new String("정말로 삭제하시겠습니까? 삭제하실려면 Y 혹은 y를 입력해주세요.");
        String yesNo = new String(ScannerUtil.nextLine(scanner, message));
        
        // String 에는 대소문자 구별 없이 특정 스트링과 같은지를 확인하는
        // equalsIgnoreCase() 라는 메소드가 있다.
        
        if(yesNo.equalsIgnoreCase("Y")) {
            // 사용자가 삭제에 동의를 하였으므로
            // list에서 index를 기준으로 해서 remove해준다.
            list.remove(index);
            // 위의 list.remove(index)가 실행되고 나서는
            // 우리가 아무것도 하지 않아도
            // delete(index)의 마지막 줄 -> printOne(index)의 마지막 줄
            // 로 이동하기 때문에 
            // 자동으로 printAll()의 무한루프로 들어가게 된다.
        }else {
            // 사용자가 의도하였건 의도하지 않았던 간에 
            // y를 입력하지 않았으므로
            // 다시 printOne(index) 메소드를 실행시켜 준다. 
            printOne(index);
        }
        
    }
    
}
