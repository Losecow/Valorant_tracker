package src;

public class Valorant {
    private String name;
    private String map;
    private int kill;
    private int death;
    private int assist;
    private int headshot;
    private int totalDamage;
    private int headshotPercentage;
    private String gameDate;

    public Valorant() {
    }

    public Valorant(String name, String map, int kill, int death, int assist, int headshot, int totalDamage, int headshotPercentage, String gameDate) {
        this.name = name;
        this.map = map;
        this.kill = kill;
        this.death = death;
        this.assist = assist;
        this.headshot = headshot;
        this.totalDamage = totalDamage;
        this.headshotPercentage = headshotPercentage;
        this.gameDate = gameDate;
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

    public int getHeadshot() { return headshot; }
    public void setHeadshot(int headshot) { this.headshot = headshot; }

    public int getTotalDamage() { return totalDamage; }
    public void setTotalDamage(int totalDamage) { this.totalDamage = totalDamage; }

    public int getHeadshotPercentage() { return headshotPercentage; }
    public void setHeadshotPercentage(int headshotPercentage) { this.headshotPercentage = headshotPercentage; }

    public String getGameDate() { return gameDate; }
    public void setGameDate(String gameDate) { this.gameDate = gameDate; }

}
