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
    
}
