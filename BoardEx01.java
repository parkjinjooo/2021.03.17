package day0317;

import java.util.ArrayList;

// 게시판 프로그램 

// 게시글에는 제목, 작성자, 내용의 내용이 있다. 
// 이 프로그램은 총 6개의 게시글을 관리할 수 있다.
// 단, 중복된 제목의 글은 작성할 수 없다. 
// 또한 입력과 출력은 분리되어있다.

import java.util.Scanner;
import day0317.Board;
import util.ScannerUtil;

public class BoardEx01 {
    // Board 객체를 저장하고 관리할 ArrayList<Board> 객체
    private static ArrayList<Board> list = new ArrayList<>();
    // 키보드 입력을 처리해줄 Scanner 객체
    private static Scanner scanner = new Scanner(System.in);
    // 글번호를 자동으로 넣어줄때 사용될 int 변수
    private static int currentId = 1;

    // 메인 메소드
    public static void main(String[] args) {
        showMenu();
    }

    // 메뉴를 보여주는 메소드
    private static void showMenu() {
        while (true) {
            System.out.println("============================");
            System.out.println("        게시판 관리 프로그램         ");
            System.out.println("============================");
            String message = new String("1. 글 쓰기 2. 글 목록 보기 3. 종료");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if (userChoice == 1) {
                // 글 작성 담당 메소드 호출
                insert();

            } else if (userChoice == 2) {
                // 글 목록 보기 담당 메소드 호출
                printAll();

            } else if (userChoice == 3) {
                // 메시지 출력 후 종료
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }

        }
    }

    // 글 작성을 담당하는 메소드
    private static void insert() {
        // 정보를 담을 Board 객체 생성
        Board b = new Board();

        // 출력 메시지를 저장할 String 객체 message 선언
        String message;
        // Board 객체에 제목을 넣고

        if (list.size() < 6) {

            message = new String("글의 제목를 입력하세요.");
            b.setTitle(ScannerUtil.nextLine(scanner, message));
            // 중복이면 다시 입력을 받게 한다.

            while (list.contains(b)) {
                System.out.println(" 중복된 글 제목은 사용하실 수 없습니다.");
                b.setTitle(ScannerUtil.nextLine(scanner, message));
            }

            // 나머지 정보 입력
            // 전역변수 currentIndex의 값을 넣고, currentIndex의 값을 1 증가시킨다.

            b.setId(currentId++);

            // 글의 작성자를 입력 받는다.
            message = new String("글의 작성자를 입력하세요.");
            b.setUser(ScannerUtil.nextLine(scanner, message));

            // 글의 내용을 입력 받는다.
            message = new String("글의 내용를 입력하세요.");
            b.setContent(ScannerUtil.nextLine(scanner, message));

            // BoardList에 b 추가
            list.add(b);
        } else {
            System.out.println("더이상 입력하실 수 없습니다. ");

        }

    }

    // 글 목록보기를 담당하는 메소드
    private static void printAll() {
        while (true) {
            // 만약 글이 list에 하나도 없을 시에는
            // 메시지 출력 후 break를 통해, 무한루프를 종료 시킨다.

            if (list.size() == 0) {
                System.out.println();
                System.out.println("아직 등록된 글이 없습니다.");
                System.out.println();
            }
            System.out.println();

            // for문을 통하여
            // list의 인덱스와 글 제목을 출력해준다.
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%3d. %s\n", i + 1, list.get(i).getTitle());
            }

            // 사용자로부토 개별보기할 글의 인덱스나 혹은 뒤로가기를 선택하도록 입력을 받는다.
            String message = new String("개별 보기할 게시물의 번호를 선택해주세요. 만약 메뉴로 가시려면 0을 입력해주세요.");
            int userChoice = ScannerUtil.nextInt(scanner, message, 0, list.size()) - 1;

            // 뒤로가기를 할 경우, break를 통해 showMenu() 메소드로 돌아가게 만들어준다.
            if (userChoice == -1) {
                System.out.println();
                System.out.println("메뉴로 돌아갑니다.");
                System.out.println();
                break;
            } else {
                // 개별 보기할 경우, 해당 메소드를 호출해준다.
                printOne(userChoice);
            }

        }
    }

    // 글 개별보기를 담당하는 메소드
    private static void printOne(int index) {
        // list의 index 번에 저장된 객체의 printBoard() 메소드를 실행한다.
        list.get(index).printBoard();

        // 사용자에게 수정, 삭제, 뒤로가기 중 하나를 선택하도록 입력을 받는다.
        String message = new String("1. 수정 2. 삭제 3. 뒤로가기");
        int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);

        if (userChoice == 1) {
            // 수정을 선택하면 update 메소드를 실행해준다.
            update(index);

        } else if (userChoice == 2) {
            // 삭제를 선택하면 delete 메소드를 실행해준다.
            delete(index);
        }
        // 뒤로가기를 선택하면 아무것도 안한다.
    }

    // 글 수정을 담당하는 메소드
    private static void update(int index) {
        // 1. 제목이 중복인지부터 체크하기 위해
        // 임시로 Board 객체를 하나 만들어서
        // 그 객체에 제목을 넣고
        // 그 객체가 list에 contains() 메소드에서
        // true가 나오는지 확인하여
        // false가 나올때까지 제목을 다시 입력을 받는다.

        String message = new String("글의 제목를 입력하세요.");
        Board b = new Board();
        b.setTitle(ScannerUtil.nextLine(scanner, message));

        while (list.contains(b) && !b.equals(list.get(index))) {
            System.out.println(" 중복된 제목입니다. 다시 입력해주세요.");
            b.setTitle(ScannerUtil.nextLine(scanner, message));
        }

        // 2. 임시로 만든 Board객체의 제목을 list의 index번 객체에 저장한다.
        list.get(index).setTitle(b.getTitle());

        // 3. 그 외의 정보를 list의 index번 객체에 저장한다.

        message = new String("작성자를 수정해주세요.");
        list.get(index).setUser(ScannerUtil.nextLine(scanner, message));

        message = new String("내용를 수정해주세요.");
        list.get(index).setContent(ScannerUtil.nextLine(scanner, message));

        // 4. 다시 개별글 보기 메소드를 실행한다.
        printOne(index);
    }

    // 글 삭제를 담당하는 메소드
    private static void delete(int index) {
        // 1. 사용자로부터 정말로 삭제할지 물어봐서 사용자로부터 입력을 받는다.
        String message = new String(" 정말로 삭제하시겠습니까? 삭제하시려면 Y 혹은 y 입력해주세요.");
        String yesNo = ScannerUtil.nextLine(scanner, message);

        if (yesNo.equalsIgnoreCase("Y")) {
            // 2. 사용자가 대소문자 y를 입력했을 경우, 해당 index를 삭제한다.
            list.remove(index);

        } else {
            // 3. 그 외에 다른 글자를 입력했을 경우, printOne(index)를 실행한다.
            printOne(index);
        }

    }

}