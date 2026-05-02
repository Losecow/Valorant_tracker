package src;

// 공통적이고 필수적인 핵심 기능만 -> 부가적 기능은 X
public interface ICRUD {
    int addData();
    void updateData(int no);
    void deleteData(int no);
    void printData();
}
