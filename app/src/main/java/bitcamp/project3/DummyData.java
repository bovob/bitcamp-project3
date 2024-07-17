package bitcamp.project3;

import bitcamp.project3.vo.Book;

import java.util.ArrayList;
import java.util.List;

public class DummyData {
    //더미생성
    public static List<Book> addDummyBook() {
        List<Book> booklist = new ArrayList<>();
        String[] categories = {"소설", "과학", "역사", "자기 계발", "철학"};
                              //NOV     SCI     HIS     SEL         PHI
        final String NOV = categories[0];
        final String SCI = categories[1];
        final String HIS = categories[2];
        final String SEL = categories[3];
        final String PHI = categories[4];

        String[][] dummy = {
                {"우리는 왜 서로를 미워하는가", "에즈라클라인",SEL, "istj"},
                {"부자의 언어", "존소포릭", SEL, "istj"},

                {"우리는 SF를 좋아해", "김보영", SEL, "intp"},
                {"열한계단", "채사장", PHI, "isfj"},


                {"죽어 마땅한 사람들", "피터스완슨", NOV, "infj"},


                {"마션","앤디위어",SCI,"intj"},


                {"사람을 움직이는 처세술","데일카네기",SEL,"istp"},


                {"누구나 철학자가 되는 밤","김한승",SEL,"isfp"},


                {"타인의 해석","말콤글래드웰",SEL,"infp"},


                {"프리다칼로","프리다칼로",HIS,"intp"},


                {"Demian","헤르만헤세", NOV, "estp"},


                {"포노사피엔스","최재봉",SCI, "esfp"},


                {"세상에서 가장 짧은 교양수업","데이비드",SEL,"enfp"},


                {"파피용","베르나르베르베르",NOV,"entp"},


                {"유대인의 생각훈련","심정섭",SEL,"estj"},


                {"행복한 이기주의자","웨인다이어",SEL,"esfj"},


                {"나의 라임오렌지나무","바스콘셀로스",NOV,"enfj"},

                
                {"다정히도 불어오는 바람","윤동주",NOV,"entj"}

        };

        int i=0;
        for(String[] dum : dummy){
            Book book = new Book(dum);
            book.setNo(++i);
            booklist.add(book);
        }

        return booklist;
    }
}
