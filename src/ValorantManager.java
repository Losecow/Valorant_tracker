package src;

import java.util.ArrayList;
import java.util.Scanner;

public class ValorantManager implements ICRUD {

    // 게임 플레이 기록 목록
    private ArrayList<Valorant> list = new ArrayList<>();
    private Scanner sc;
    private final String FILE_NAME = "valorant_data.txt";

    // 생성자
    public ValorantManager(Scanner sc) {
        this.sc = sc;
        loadFile(); // 파일 불러오기
    }

    @Override
    public int addData() {

        // 요원 이름
        System.out.print("요원 이름: ");
        String name = sc.nextLine().trim();

        // 맵
        System.out.print("맵: ");
        String map = sc.nextLine().trim();
        
        // Kill
        System.out.print("Kill: ");
        int kill = sc.nextInt();
        sc.nextLine();

        // Death
        System.out.print("Death: ");
        int death = sc.nextInt();
        sc.nextLine();
        
        // Assist
        System.out.print("Assist: ");
        int assist = sc.nextInt();
        sc.nextLine();

        // Headshot Percentage
        System.out.print("Headshot Percentage: ");
        int headshotPercentage = sc.nextInt();
        sc.nextLine();

        // Game Date
        System.out.print("Game Date: ");
        String gameDate = sc.nextLine().trim();
        
        // TRS
        System.out.print("TRS: ");
        int trs = sc.nextInt();
        sc.nextLine();

        // Valorant 객체 생성
        Valorant valorant = new Valorant(name, map, kill, death, assist, headshotPercentage, gameDate, trs);
        list.add(valorant);

        // 게임 플레이 기록 추가 메시지 출력
        System.out.println("게임 플레이 기록이 추가되었습니다.");
        return 1;
    }

    @Override
    public void printData() {
        System.out.println("총 " + list.size() + "개의 게임 플레이 기록이 있습니다.");
        System.out.println("========================================================================================");
        System.out.println(" No |    날짜    |  요원  |   맵   |  K/D/A  | 헤드샷(%) |  TRS ");
        System.out.println("----------------------------------------------------------------------------------------");
        int i = 1;
        for (Valorant item : list) {
            System.out.println(i + " | " + item.toString());
            i++;
        }
    }

    // 게임 플레이 기록 수정
    // 수정할 번호 선택
    @Override
    public void updateData(int no) {
        if (no < 1 || no > list.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }
        
        Valorant valorant = list.get(no - 1);
    }

    // 게임 플레이 기록 삭제
    // 삭제할 번호 선택
    @Override
    public void deleteData(int no) {
        if (no < 1 || no > list.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }
        list.remove(no - 1);
    }
}
