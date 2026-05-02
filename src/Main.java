package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ValorantTrackerManager manager = new ValorantTrackerManager(sc);
        int datano;
        boolean quit = false;

        do {
            System.out.println("\n=== 발로란트 게임 플레이 트래커 ===");
            System.out.println("1. 게임 플레이 기록 추가 (Add)");
            System.out.println("2. 전체 기록 조회 (List)");
            System.out.println("3. 게임 플레이 기록 수정 (Update)");
            System.out.println("4. 게임 플레이 기록 삭제 (Delete)");
            // System.out.println("5. 게임 플레이 기록 검색 (Search)");
            // System.out.println("6. 게임 플레이 기록 통계 (Most/Min 정렬)");
            // System.out.println("7. 일별 헤드샷 비율 필터링 (Filter)");
            // System.out.println("8. 파일에 저장 (Save)");
            System.out.println("0. 종료 (Quit)");
            System.out.print("메뉴 선택 > ");
            

            String input = sc.nextLine().trim();
            int menu;
            try {
                menu = Integer.parseInt(input);
            // NumberFormatException -> 숫자를 입력하지 않았을 때 발생하는 예외
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
                continue;
            }

            switch (menu) {
                case 1:
                    manager.addData();
                    break;
                case 2:
                    manager.printData();
                    break;
                case 3:
                    manager.printData();
                    System.out.print("수정할 번호 선택 > ");
                    datano = Integer.parseInt(sc.nextLine().trim());
                    manager.updateData(datano);
                    break;
                case 4:
                    manager.printData();
                    System.out.print("삭제할 번호 선택 > ");
                    datano = Integer.parseInt(sc.nextLine().trim());
                    manager.deleteData(datano);
                    break;
                // case 5:
                //     manager.searchByDate();
                //     break;
                // case 6:
                //     manager.showGamePlayStatistics();
                //     break;
                // case 7:
                //     manager.filterByHeadshotRate();
                //     break;
                // case 8:
                //     manager.saveFile();
                //     break;
                case 0:
                    manager.saveFile(); // 종료 시 자동 저장
                    quit = true;
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다.");
            }
        } while (!quit);
    }
}
