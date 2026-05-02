package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ValorantManager implements ICRUD {

    // 게임 플레이 기록 목록
    private ArrayList<Valorant> list = new ArrayList<>();
    private Scanner sc;
    private final String FILE_NAME = "valorant_data.txt";

    // 생성자
    public ValorantManager(Scanner sc) {
        this.sc = sc;
        loadFile();
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
        System.out.print("Game Date(YYYY-MM-DD): ");
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
        System.out.println("=======================================================================");
        System.out.println(" No |    날짜    |  요원   |     맵     |    K/D/A    | 헤드샷(%) | TRS ");
        System.out.println("-----------------------------------------------------------------------");
        int i = 1;
        for (Valorant item : list) {
            System.out.printf("%-3d |%s\n", i, item.toString());
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
        
        // for문을 돌리지 않고 list.get(no - 1)을 사용해 한 번에 데이터를 가져옴 (ArrayList 사용이유)
        Valorant valorant = list.get(no - 1);
        
        System.out.println("수정할 데이터를 입력해주세요.");
        System.out.println("========================================================================================");

        System.out.print("새 요원 이름 (" + valorant.getName() + "): ");
        String name = sc.nextLine().trim();
        // 수정할 데이터가 비어있지 않으면 수정하고 비어있으면 기존 데이터 유지
        if (!name.isEmpty()) valorant.setName(name);

        System.out.print("New Map (" + valorant.getMap() + "): ");
        String map = sc.nextLine().trim();
        if (!map.isEmpty()) valorant.setMap(map);

        System.out.print("New Kill (" + valorant.getKill() + "): ");
        String killStr = sc.nextLine().trim();
        if (!killStr.isEmpty()) valorant.setKill(Integer.parseInt(killStr));

        System.out.print("New Death (" + valorant.getDeath() + "): ");
        String deathStr = sc.nextLine().trim();
        if (!deathStr.isEmpty()) valorant.setDeath(Integer.parseInt(deathStr));

        System.out.print("New Assist (" + valorant.getAssist() + "): ");
        String assistStr = sc.nextLine().trim();
        if (!assistStr.isEmpty()) valorant.setAssist(Integer.parseInt(assistStr));

        System.out.print("New Headshot Percentage (" + valorant.getHeadshotPercentage() + "): ");
        String headshotPercentageStr = sc.nextLine().trim();
        if (!headshotPercentageStr.isEmpty()) valorant.setHeadshotPercentage(Integer.parseInt(headshotPercentageStr));

        System.out.print("New Game Date (" + valorant.getGameDate() + "): ");
        String gameDate = sc.nextLine().trim();
        if (!gameDate.isEmpty()) valorant.setGameDate(gameDate);

        System.out.print("New TRS (" + valorant.getTrs() + "): ");
        String trsStr = sc.nextLine().trim();
        if (!trsStr.isEmpty()) valorant.setTrs(Integer.parseInt(trsStr));

        System.out.println("========================================================================================");
        System.out.println("수정되었습니다.");
        System.out.println("========================================================================================");
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

        System.out.println("========================================================================================");
        System.out.println("삭제되었습니다.");
        System.out.println("========================================================================================");
    }

    // 부가기능 1: 게임 플레이 기록 검색 (날짜 기준)
    public void searchByDate(String date) {
        int count = 0;
        System.out.println("=======================================================================");
        System.out.println(" No |    날짜    |  요원   |     맵     |    K/D/A    | 헤드샷(%) | TRS ");
        System.out.println("-----------------------------------------------------------------------");
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getGameDate().contains(date)) {
                System.out.printf("%-3d |%s\n", (i + 1), list.get(i).toString());
                count++;
            }
        }
        
        System.out.println("=======================================================================");
        System.out.println("총 " + count + "건 검색됨.");
        System.out.println("=======================================================================");
    }

    // 부가기능 2: 게임 플레이 기록 통계 (가장 많이 플레이한 요원, 맵, 평균 KDA, 평균 헤드샷 비율)
    public void showGamePlayStatistics() {
        if (list.isEmpty()) {
            System.out.println("데이터가 없습니다.");
            return;
        }

        // 1. 요원별 / 맵별 플레이 횟수 카운트
        // HashMap 설명 : 키와 값의 쌍으로 이루어진 데이터를 저장하는 자료구조 (Key는 중복 불가능, Value는 중복 가능)
        // 예: Map<String, Integer> agentCount = new HashMap<>(); // 요원 이름을 키로, 플레이 횟수를 값으로 저장
        // 예: agentCount.put("요원 이름", 플레이 횟수); // 요원 이름을 키로, 플레이 횟수를 값으로 저장 (이미 있으면 값을 증가)
        // 예: agentCount.get("요원 이름"); // 요원 이름을 키로, 플레이 횟수를 값으로 가져옴 (이미 있으면 값을 반환)
        // 예: agentCount.getOrDefault("요원 이름", 0); // 요원 이름을 키로, 플레이 횟수를 값으로 가져옴 (이미 있으면 값을 반환, 없으면 0을 반환)
        // 예: agentCount.putIfAbsent("요원 이름", 플레이 횟수); // 요원 이름을 키로, 플레이 횟수를 값으로 저장 (이미 있으면 값을 증가하지 않음)
        Map<String, Integer> agentCount = new HashMap<>();
        Map<String, Integer> mapCount = new HashMap<>();
        
        // 2. 전체 평균을 위한 합계 변수
        // totalKill // 전체 킬 합계
        // totalDeath // 전체 데스 합계
        // totalAssist // 전체 어시스트 합계
        // totalHeadshotPercentage // 전체 헤드샷 비율 합계
        int totalKill = 0;
        int totalDeath = 0;
        int totalAssist = 0;
        int totalHeadshotPercentage = 0;

        for (Valorant item : list) {
            // 카운트 누적
            // 요원별 플레이 횟수 카운트
            agentCount.put(item.getName(), agentCount.getOrDefault(item.getName(), 0) + 1); // 요원 이름을 키로, 플레이 횟수를 값으로 저장 (이미 있으면 값을 증가)
            // 맵별 플레이 횟수 카운트
            mapCount.put(item.getMap(), mapCount.getOrDefault(item.getMap(), 0) + 1); // 맵 이름을 키로, 플레이 횟수를 값으로 저장 (이미 있으면 값을 증가)
            
            // KDA 및 헤드샷 합계 누적
            totalKill += item.getKill(); // 전체 킬 합계
            totalDeath += item.getDeath(); // 전체 데스 합계
            totalAssist += item.getAssist(); // 전체 어시스트 합계
            totalHeadshotPercentage += item.getHeadshotPercentage(); // 전체 헤드샷 비율 합계
        }

        // 가장 많이 플레이한 요원 찾기
        // mostPlayedAgent // 가장 많이 플레이한 요원
        // maxAgentCount // 가장 많이 플레이한 요원의 플레이 횟수
        // entry // 요원 이름을 키로, 플레이 횟수를 값으로 가져옴
        // entry.getValue() // 플레이 횟수
        // entry.getKey() // 요원 이름
        // entry.getValue() > maxAgentCount // 플레이 횟수가 가장 많은 요원 찾기
        // maxAgentCount = entry.getValue(); // 플레이 횟수를 가장 많은 요원의 플레이 횟수로 설정
        // mostPlayedAgent = entry.getKey(); // 요원 이름을 가장 많이 플레이한 요원으로 설정
        String mostPlayedAgent = "";
        int maxAgentCount = 0;
        for (Map.Entry<String, Integer> entry : agentCount.entrySet()) {
            if (entry.getValue() > maxAgentCount) {
                maxAgentCount = entry.getValue();
                mostPlayedAgent = entry.getKey();
            }
        }

        // 가장 많이 플레이한 맵 찾기
        // mostPlayedMap // 가장 많이 플레이한 맵
        // maxMapCount // 가장 많이 플레이한 맵의 플레이 횟수
        // entry // 맵 이름을 키로, 플레이 횟수를 값으로 가져옴
        // entry.getValue() // 플레이 횟수
        // entry.getKey() // 맵 이름
        // entry.getValue() > maxMapCount // 플레이 횟수가 가장 많은 맵 찾기
        // maxMapCount = entry.getValue(); // 플레이 횟수를 가장 많은 맵의 플레이 횟수로 설정
        // mostPlayedMap = entry.getKey(); // 맵 이름을 가장 많이 플레이한 맵으로 설정
        String mostPlayedMap = "";
        int maxMapCount = 0;
        for (Map.Entry<String, Integer> entry : mapCount.entrySet()) {
            if (entry.getValue() > maxMapCount) {
                maxMapCount = entry.getValue();
                mostPlayedMap = entry.getKey();
            }
        }

        // 평균 계산 (소수점 첫째 자리까지)
        // totalGames // 전체 플레이 판수
        // avgKill // 전체 킬 평균
        // avgDeath // 전체 데스 평균
        // avgAssist // 전체 어시스트 평균
        // avgHeadshot // 전체 헤드샷 비율 평균
        int totalGames = list.size();
        double avgKill = (double) totalKill / totalGames;
        double avgDeath = (double) totalDeath / totalGames;
        double avgAssist = (double) totalAssist / totalGames;
        double avgHeadshot = (double) totalHeadshotPercentage / totalGames;

        System.out.println("==========================발로란트 플레이 통계=========================");
        System.out.println("총 플레이 판수: " + totalGames + "판");
        System.out.println("가장 많이 플레이한 요원: " + mostPlayedAgent + " (" + maxAgentCount + "회)");
        System.out.println("가장 많이 플레이한 맵: " + mostPlayedMap + " (" + maxMapCount + "회)");
        System.out.printf("평균 K/D/A: %.1f / %.1f / %.1f\n", avgKill, avgDeath, avgAssist);
        System.out.printf("평균 헤드샷 비율: %.1f%%\n", avgHeadshot);
        System.out.println("=======================================================================");
    }

    // 부가기능 3: 헤드샷 비율 필터링 (입력한 비율 이상인 기록만 출력)
    public void filterByHeadshotRate(int minHeadshot) {

        // minHeadshot // 최소 헤드샷 비율
        // count // 헤드샷 비율이 최소 헤드샷 비율 이상인 기록 개수
        int count = 0;
        System.out.println("=======================================================================");
        System.out.println(" No |    날짜    |  요원   |     맵     |    K/D/A    | 헤드샷(%) | TRS ");
        System.out.println("-----------------------------------------------------------------------");
        
        for (int i = 0; i < list.size(); i++) {
            // 헤드샷 비율이 최소 헤드샷 비율 이상인 기록 출력
            if (list.get(i).getHeadshotPercentage() >= minHeadshot) {
                System.out.printf("%-3d |%s\n", (i + 1), list.get(i).toString());
                count++;
            }
        }
        
        System.out.println("=======================================================================");
        System.out.println("헤드샷 " + minHeadshot + "% 이상인 기록: 총 " + count + "건");
        System.out.println("=======================================================================");
    }
    
    // 부가기능 4: 데이터 정렬 기능
    public void sortData() {
        if (list.isEmpty()) {
            System.out.println("데이터가 없습니다.");
            return;
        }

        System.out.println("정렬 기준을 선택하세요:");
        System.out.println("1. 킬(Kill)  2. 데스(Death)  3. 어시스트(Assist)  4. 헤드샷 비율(%)  5. TRS");
        System.out.print("선택 > ");
        String criteriaInput = sc.nextLine().trim();
        int criteria = 0;
        try {
            criteria = Integer.parseInt(criteriaInput);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        if (criteria < 1 || criteria > 5) {
            System.out.println("1번부터 5번 사이의 숫자를 입력해주세요.");
            return;
        }

        System.out.println("정렬 방식을 선택하세요:");
        System.out.println("1. 오름차순(asc)  2. 내림차순(desc)");
        System.out.print("선택 > ");
        String orderInput = sc.nextLine().trim();
        int order = 0;
        try {
            order = Integer.parseInt(orderInput);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
            return;
        }

        if (order != 1 && order != 2) {
            System.out.println("1번(오름차순) 또는 2번(내림차순)을 선택해주세요.");
            return;
        }

        // 오름차순이면 true, 내림차순이면 false
        final boolean isAsc = (order == 1);

        // criteria를 오버라이딩하기 위해 final로 선언
        // 설명: criteria는 정렬 기준이므로 변경되면 안됨, 따라서 final로 선언하여 변경 불가능하게 함
        final int finalCriteria = criteria;

        // Comparator를 사용한 정렬 로직 구현
        // Comparator 설명 : 두 객체를 비교하는 인터페이스
        // new Comparator<Valorant>() { // Valorant 객체를 비교하는 Comparator 객체 생성
        //     @Override // 오버라이딩
        //     public int compare(Valorant v1, Valorant v2) { // 두 객체를 비교하는 메서드
        //         return v1.getKill() - v2.getKill(); // 두 객체의 킬 값을 비교하여 오름차순 정렬
        //     }
        // });
        Collections.sort(list, new Comparator<Valorant>() {
            @Override
            public int compare(Valorant v1, Valorant v2) {
                int result = 0;
                switch (finalCriteria) {
                    case 1:
                        result = Integer.compare(v1.getKill(), v2.getKill());
                        break;
                    case 2:
                        result = Integer.compare(v1.getDeath(), v2.getDeath());
                        break;
                    case 3:
                        result = Integer.compare(v1.getAssist(), v2.getAssist());
                        break;
                    case 4:
                        result = Integer.compare(v1.getHeadshotPercentage(), v2.getHeadshotPercentage());
                        break;
                    case 5:
                        result = Integer.compare(v1.getTrs(), v2.getTrs());
                        break;
                }
                // isAsc가 true면 오름차순, false면 내림차순
                return isAsc ? result : -result; 
            }
        });

        System.out.println("=======================================================================");
        System.out.println("정렬이 완료되었습니다.");
        System.out.println("=======================================================================");
        
        // 정렬 결과 출력ㄱ
        printData();
    }

    // 부가기능 5: 파일 저장
    public void saveFile() {

        // 파일 저장 
        try {
            // PrintWriter // 파일 출력 스트림 (파일 쓰기 용)
            // new PrintWriter(FILE_NAME) // FILE_NAME 파일에 출력
            PrintWriter pw = new PrintWriter(FILE_NAME);
            for (Valorant item : list) {
                // toFileString() // 파일 저장을 위한 문자열 포맷 (Valorant 클래스에서 정의)
                pw.println(item.toFileString());
            }
            // close() // 파일 출력 스트림 닫기
            pw.close();
            System.out.println("데이터가 파일에 저장되었습니다.");
        // FileNotFoundException // 파일을 찾을 수 없을 때 발생하는 예외 
        } catch (FileNotFoundException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다.");
        }
    }

    // 부가기능 6: 파일 불러오기
    public void loadFile() {

        // 파일 존재 여부 확인
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return; // 파일이 없으면 종료
            
            // Scanner // 파일 입력 스트림 (파일 읽기 용)
            // new Scanner(file) // FILE_NAME 파일에서 읽기
            Scanner fileScanner = new Scanner(file);
            // hasNextLine() // 파일에 다음 줄이 있는지 확인
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // split() // 문자열을 구분자로 분리 (|를 구분자로 분리)
                String[] parts = line.split("\\|");
                // 파일 데이터 형식이 맞는지 확인 (필드 8개)
                if (parts.length == 8) {
                    // Valorant 객체 생성
                    Valorant valorant = new Valorant(
                        parts[0], // name
                        parts[1], // map
                        Integer.parseInt(parts[2]), // kill
                        Integer.parseInt(parts[3]), // death
                        Integer.parseInt(parts[4]), // assist
                        Integer.parseInt(parts[5]), // headshotPercentage
                        parts[6], // gameDate
                        Integer.parseInt(parts[7])  // trs
                    );
                    // list.add(valorant) // Valorant 객체를 리스트에 추가
                    list.add(valorant);
                }
            }
            // close() // 파일 입력 스트림 닫기
            fileScanner.close();
            System.out.println("=======================================================================");
            System.out.println("파일 불러오기를 시작합니다 ...");
            System.out.println("파일에서 데이터를 불러왔습니다.");
            System.out.println("=======================================================================");
        // FileNotFoundException // 파일을 찾을 수 없을 때 발생하는 예외 
        } catch (FileNotFoundException e) {
            System.out.println("=======================================================================");
            System.out.println("파일 불러오기를 시작합니다 ...");
            System.out.println("파일을 찾을 수 없습니다.");
            System.out.println("=======================================================================");
        }
    }

}
