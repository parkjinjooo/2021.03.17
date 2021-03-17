package day0317;
// ArrayList를 활용한 로또게임

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import util.ScannerUtil;

public class LottoGame01 {
    private static final int NUMBER_MAX = 45;
    private static final int NUMBER_MIN = 1;
    private static final int NUMBER_SIZE = 6;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Integer> computerList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> userList = new ArrayList<>();

        // computerList에 숫자 추가
        insertRandomNumber(computerList);

        // 사용자리스트에 게임 숫자 몇번인지 받기
        // 사용자 선택에 따라서 자동/수동 숫자 넣기
        // 위의 두가지는 우리가 decideUserGame() 메소드만 실행해주면 됨.
        decideUserGame(userList);

        // 비교해서 등수 정하기
        showResult(computerList, userList);

    }

    // 랜덤 숫자를 리스트에 추가하고 정렬해주는 메소드
    private static void insertRandomNumber(ArrayList<Integer> list) {
        Random random = new Random();
        while (list.size() < NUMBER_SIZE) {
            Integer randomNumber = random.nextInt(NUMBER_MAX) + 1;
            if (!list.contains(randomNumber)) {
                list.add(randomNumber);
            }

        }
        sortList(list);

    }

    // 사용자 숫자를 리스트에 추가하고 정렬해주는 메소드
    private static void insertUserNumber(ArrayList<Integer> list) {
        while (list.size() < NUMBER_SIZE) {
            int currentPosition = list.size() + 1;
            String message = new String(currentPosition + "번 숫자를 입력해주세요");
            int userNumber = ScannerUtil.nextInt(scanner, message, NUMBER_MIN, NUMBER_MAX);
            if (!list.contains(userNumber)) {
                list.add(userNumber);
            } else {
                System.out.println("중복돤 숫자입니다.");
            }

        }
        sortList(list);
    }

    // 사용자로부터 입력을 받아서 자동으로 해줄지 수동으로 해줄지를 결정해주는 메소드
    private static void decideUserGame(ArrayList<ArrayList<Integer>> list) {
        String message = new String("몇 번 플레이하실지 정해주세요");
        int gameNumber = ScannerUtil.nextInt(scanner, message);
        while (list.size() < gameNumber) {
            message = new String("1. 자동  2. 수동");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 2);
            ArrayList<Integer> tempList = new ArrayList<>();

            if (userChoice == 1) {
                insertRandomNumber(tempList);

            } else if (userChoice == 2) {
                insertUserNumber(tempList);

            }

            list.add(tempList);

        }
    }

    // 리스트에 숫자를 정렬해주는 메소드
    private static void sortList(ArrayList<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                Integer temp = list.set(i, list.get(i + 1));
                list.set(i + 1, temp);
                i = -1;

            }
        }
    }

    // 결과를 출력하고 확인하는 메소드
    private static void showResult(ArrayList<Integer> computerList, ArrayList<ArrayList<Integer>> userList) {
        // 컴퓨터 숫자 출력
        System.out.print("컴퓨터의 숫자 출력: [");
        for (int i = 0; i < computerList.size(); i++) {
            System.out.printf("%4d", computerList.get(i));
        }
        System.out.println(" ]");
        System.out.println();
        // for문으로 사용자 게임마다 숫자, 맞춘 갯수, 등수 출력
        for (int i = 0; i < userList.size(); i++) {
            String message = new String((i + 1) + "번 게임");
            System.out.println(message);
            System.out.print("사용자의 숫자 출력: [");
            for (int j = 0; j < userList.get(i).size(); j++) {
                System.out.printf("%4d", userList.get(i).get(j));
            }
            System.out.print(" ]");

            int count = countSame(computerList, userList.get(i));
            System.out.printf("맞은갯수: %d개 ", count);
            if (count >= 2) {
                System.out.printf("사용자의 등수: %d등\n", NUMBER_SIZE - count + 1);
            } else {
                System.out.println("사용자의 등수: 등외");
            }
            System.out.println();

        }

    }

    // 2개의 리스트를 비교하여 같은 숫자가 몇개인지 확인하는 메소드
    private static int countSame(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        int count = 0;
        for (int i = 0; i < list1.size(); i++) {
            if (list2.contains(list1.get(i))) {
                count++;
            }
        }
        return count;
    }

}
