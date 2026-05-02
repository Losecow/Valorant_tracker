package src;

public class Valorant {
    private String name;
    private String map;
    private int kill;
    private int death;
    private int assist;
    private int headshotPercentage;
    private String gameDate;
    private int trs; // Tracker Score

    public Valorant() {
    }

    public Valorant(String name, String map, int kill, int death, int assist, int headshotPercentage, String gameDate, int trs) {
        this.name = name;
        this.map = map;
        this.kill = kill;
        this.death = death;
        this.assist = assist;
        this.headshotPercentage = headshotPercentage;
        this.gameDate = gameDate;
        this.trs = trs;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMap() { return map; }
    public void setMap(String map) { this.map = map; }

    public int getKill() { return kill; }
    public void setKill(int kill) { this.kill = kill; }

    public int getDeath() { return death; }
    public void setDeath(int death) { this.death = death; }

    public int getAssist() { return assist; }
    public void setAssist(int assist) { this.assist = assist; }

    public int getTrs() { return trs; }
    public void setTrs(int trs) { this.trs = trs; }

    public int getHeadshotPercentage() { return headshotPercentage; }
    public void setHeadshotPercentage(int headshotPercentage) { this.headshotPercentage = headshotPercentage; }

    public String getGameDate() { return gameDate; }
    public void setGameDate(String gameDate) { this.gameDate = gameDate; }

    @Override
    public String toString() {
        String kda = String.format("%d/%d/%d", kill, death, assist);
        return String.format(" %-10s | %-7s | %-10s | %-11s | %-9d | %-3d ", 
                gameDate, name, map, kda, headshotPercentage, trs);
    }

    // 파일 저장을 위한 문자열 포맷 (StringBuilder 활용)
    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("|")
          .append(map).append("|")
          .append(kill).append("|")
          .append(death).append("|")
          .append(assist).append("|")
          .append(headshotPercentage).append("|")
          .append(gameDate).append("|")
          .append(trs);
        return sb.toString();
    }

}
